import 'package:firebase_auth/firebase_auth.dart';
import 'package:frontend/model/address.dart';
import 'package:frontend/data/addresses.dart';
import 'package:frontend/utils.dart';
import 'package:frontend/widget/scrollable_widget.dart';
import 'package:frontend/widget/text_dialog_widget.dart';
import 'package:flutter/material.dart';
import 'package:frontend/widget/navigation_drawer_widget.dart';
import 'package:firebase_database/firebase_database.dart';

class AddressPage extends StatefulWidget {
  const AddressPage({Key? key}) : super(key: key);

  @override
  _AddressPageState createState() => _AddressPageState();
}

class _AddressPageState extends State<AddressPage> {
  late List<Address> addresses;
  final database = FirebaseDatabase.instance.ref();
  final userId = FirebaseAuth.instance.currentUser!.uid;
  late TextEditingController nameController;
  late TextEditingController destController;
  late TextEditingController descController;

  @override
  void initState() {
    super.initState();
    addresses = List.of(allAddress);
    nameController = TextEditingController();
    destController = TextEditingController();
    descController = TextEditingController();
  }

  @override
  void dispose(){
    nameController.dispose();
    destController.dispose();
    descController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final postKey = database.child('users/$userId/addressbook/').push().key;
    final Map<String, Map> updates = {};

    return Scaffold(
      drawer: const NavigationDrawerWidget(),
      body: Center(
          child: Column(
            children: <Widget>[
              ScrollableWidget(child: buildDataTable(),
              ), Center(
                  child: Row(
                      children: <Widget>[
                        Expanded(child: ElevatedButton(
                            onPressed: () async {
                              final newEntry = await addNewAddressEntry();
                              final newEntry2 = newEntry?.toJson();
                              updates['/users/$userId/addressbook/$postKey'] = newEntry2!;

                              setState(() => [
                                addresses.add(newEntry!),
                                database.update(updates),
                              ]);
                            }, child: const Text('Add entry')
                        )),
                        Expanded(child: ElevatedButton(
                            onPressed: () {
                              setState(() {
                                updateAddress();
                              });
                            },
                            child: const Text('Update Table')
                        ))
                      ]))
            ],
          )
      ),
      appBar: AppBar(
        title: const Text('Address Book'),
        centerTitle: true,
        backgroundColor: Colors.green,
      ),
    );
  }

  Widget buildDataTable() {
    final columns = ['Name', 'Destination', 'Description'];
    return DataTable(
      columns: getColumns(columns),
      rows: getRows(addresses),
      border: TableBorder.symmetric(
          inside: const BorderSide(
            width: 2,
          )
      ),
    );
  }

  List<DataColumn> getColumns(List<String> columns) {
    return columns.map((String column) {
      final isDescription = column == columns[2];

      return DataColumn(
        label: Text(column),
        numeric: isDescription,
      );
    }).toList();
  }

  List<DataRow> getRows(List<Address> addresses) => addresses.map((Address address) {
    final cells = [address.name, address.destination, address.description];
    return DataRow(
      cells: Utils.modelBuilder(cells, (index, cell) {
        final showEditIcon = index == 0 || index == 1;
        return DataCell(
          Text('$cell'),
          showEditIcon: showEditIcon,
          onTap: () {
            switch (index) {
              case 0:
                editName(address);
                break;
              case 1:
                editDest(address);
                break;
            }
          },
        );
      }),
    );
  }).toList();

  Future editName(Address editAddress) async {
    final name = await showTextDialog(
      context,
      title: 'Change First Name',
      value: editAddress.name,
    );
    setState(() => addresses = addresses.map((address) {
      final isEditedAddress = address == editAddress;
      return isEditedAddress ? address.copy(name: name) : address;
    }).toList());
  }
  Future editDest(Address editAddress) async {
    final destination = await showTextDialog(
      context,
      title: 'Change Last Name',
      value: editAddress.destination,
    );
    setState(() => addresses = addresses.map((address) {
      final isEditedAddress = address == editAddress;
      return isEditedAddress ? address.copy(destination: destination) : address;
    }).toList());
  }

  Future<Address?> addNewAddressEntry() => showDialog<Address>(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('New Address'),
        content: SingleChildScrollView(
            child: SizedBox(
              width: double.infinity,
              child: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    TextField(
                      autofocus: true,
                      decoration: const InputDecoration(hintText: 'Name'),
                      controller: nameController,
                    ),
                    TextField(
                      decoration: const InputDecoration(hintText: 'Destination'),
                      controller: destController,
                    ),
                    TextField(
                      decoration: const InputDecoration(hintText: 'Description'),
                      controller: descController,
                    ),
                  ]),
            )
        ),
        actions: [
          TextButton(child: const Text('Submit'),
            onPressed: submit,
          )
        ],
      )
  );

  void submit(){
    final newAddress = Address(name: nameController.text, destination: destController.text, description: descController.text);
    Navigator.of(context).pop(newAddress);
  }

  Future<void> updateAddress() async{
    final snapshot =  database.child('users/$userId/workinghours/');
    snapshot.onValue.listen((DatabaseEvent event) {
      for (final child in event.snapshot.children){
        var dbAddress = Address.fromJson(child.value as Map<dynamic, dynamic>);
        addresses.add(dbAddress);
      }
    });
  }

}