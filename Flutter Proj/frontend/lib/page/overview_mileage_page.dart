import 'package:firebase_auth/firebase_auth.dart';
import 'package:frontend/data/mileage_data.dart';
import 'package:frontend/utils.dart';
import 'package:frontend/widget/scrollable_widget.dart';
import 'package:frontend/widget/text_dialog_widget.dart';
import 'package:flutter/material.dart';
import 'package:frontend/widget/navigation_drawer_widget.dart';
import 'package:firebase_database/firebase_database.dart';
import '../data/mileage_data.dart';
import '../model/mileage.dart';

class OverviewMileagePage extends StatefulWidget {
  const OverviewMileagePage({Key? key}) : super(key: key);

  @override
  _OverviewMileagePageState createState() => _OverviewMileagePageState();
}

class _OverviewMileagePageState extends State<OverviewMileagePage> {
  late List<Mileage> mileageList;
  final database = FirebaseDatabase.instance.ref();
  final userId = FirebaseAuth.instance.currentUser!.uid;
  late TextEditingController nameController;
  late TextEditingController dateController;
  late TextEditingController startController;
  late TextEditingController endController;
  late TextEditingController distanceController;

  @override
  void initState() {
    super.initState();
    mileageList = List.of(allMileage);

    nameController = TextEditingController();
    dateController = TextEditingController();
    startController = TextEditingController();
    endController = TextEditingController();
    distanceController = TextEditingController();
  }

  @override
  void dispose(){
    nameController.dispose();
    dateController.dispose();
    startController.dispose();
    endController.dispose();
    distanceController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final postKey = database.child('users/$userId/mileage/').push().key;
    final Map<String, Map> updates = {};

    return Scaffold(
      drawer: const NavigationDrawerWidget(),
      body: Center(
          child: Column(
            children: <Widget>[
              ScrollableWidget(child: buildDataTable()),
              Center(
                child: Row(
                  children: <Widget>[
                    Expanded(child: ElevatedButton(onPressed: () async {
                      final newEntry = await addNewMileageEntry();
                      final newEntry2 = newEntry?.toJson();
                      updates['/users/$userId/mileage/$postKey'] = newEntry2!;
                      setState(() => [
                        mileageList.add(newEntry!),
                        database.update(updates),
                      ]);
                    }, child: const Text('Add entry'))
                    ),
                    Expanded(child: ElevatedButton(onPressed: () {
                      setState(() {
                        updateMileage();
                      });
                    }, child: const Text('Update Table'))
                    )],
                )
              )
            ],
          )
      ),
      appBar: AppBar(
        title: const Text('Mileage Overview'),
        centerTitle: true,
        backgroundColor: Colors.green,
      ),
    );
  }

  Widget buildDataTable() {
    final columns = ['Name', 'Date', 'Start Address', 'End Address', 'Distance (km)', 'Status'];
    return DataTable(
      columns: getColumns(columns),
      rows: getRows(mileageList),
      border: TableBorder.symmetric(
          inside: const BorderSide(
            width: 2,
          )
      ),
    );
  }

  List<DataColumn> getColumns(List<String> columns) {
    return columns.map((String column) {
      return DataColumn(
        label: Text(column),
      );
    }).toList();
  }

  List<DataRow> getRows(List<Mileage> mileageList) => mileageList.map((Mileage mileage) {
    final cells = [mileage.name, mileage.date, mileage.startAddress, mileage.endAddress, mileage.distance, mileage.status];
    return DataRow(
      cells: Utils.modelBuilder(cells, (index, cell) {
        final showEditIcon = index == 5;
        return DataCell(
          Text('$cell'),
          showEditIcon: showEditIcon,
          onTap: () {
            switch (index) {
              case 5:
                editStatus(mileage);
                break;
            }
          },
        );
      }),
    );
  }).toList();

  Future editStatus(Mileage editMileage) async {
    final status = await showTextDialog(
      context,
      title: 'Change Status',
      value: editMileage.status,
    );

    setState(() => mileageList = mileageList.map((mileage) {
      final isEditedMileage = mileage == editMileage;

      return isEditedMileage ? mileage.copy(status: status) : mileage;
    }).toList());
  }
  Future<Mileage?> addNewMileageEntry() => showDialog<Mileage>(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('New Mileage Entry'),
        content: SingleChildScrollView(
            child: SizedBox(
              width: double.infinity,
              child: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    TextField(
                      autofocus: true,
                      decoration: const InputDecoration(hintText: 'Description'),
                      controller: nameController,
                    ),
                    TextField(
                      decoration: const InputDecoration(hintText: 'Date (DD-MM-YYYY)'),
                      controller: dateController,
                    ),
                    TextField(
                      decoration: const InputDecoration(hintText: 'Start Address'),
                      controller: startController,
                    ),
                    TextField(
                      decoration: const InputDecoration(hintText: 'End Address'),
                      controller: endController,
                    ),
                    TextField(
                      decoration: const InputDecoration(hintText: 'Distance (KM)'),
                      controller: distanceController,
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
    final mileage = Mileage(name: nameController.text, date: dateController.text, startAddress: startController.text, endAddress: endController.text, distance: int.parse(distanceController.text), status: 'Submitted');
    Navigator.of(context).pop(mileage);
  }

  Future<void> updateMileage() async{
    final snapshot =  database.child('users/$userId/mileage/');
    snapshot.onValue.listen((DatabaseEvent event) {
      for (final child in event.snapshot.children){
        var dbMileage = Mileage.fromJson(child.value as Map<dynamic, dynamic>);
        mileageList.add(dbMileage);
      }
    });
  }
}