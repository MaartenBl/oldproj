import 'package:firebase_auth/firebase_auth.dart';
import 'package:frontend/data/hours_data.dart';
import 'package:frontend/model/hours.dart';
import 'package:frontend/utils.dart';
import 'package:frontend/widget/scrollable_widget.dart';
import 'package:frontend/widget/text_dialog_widget.dart';
import 'package:flutter/material.dart';
import 'package:frontend/widget/navigation_drawer_widget.dart';
import 'package:firebase_database/firebase_database.dart';

class OverviewHoursPage extends StatefulWidget {
  const OverviewHoursPage({Key? key}) : super(key: key);

  @override
  _OverviewHoursPageState createState() => _OverviewHoursPageState();
}

class _OverviewHoursPageState extends State<OverviewHoursPage> {
  late List<Hours> workingHours;
  final database = FirebaseDatabase.instance.ref();
  final userId = FirebaseAuth.instance.currentUser!.uid;
  late TextEditingController projectController;
  late TextEditingController hoursController;
  late TextEditingController dateController;

  @override
  void initState() {
    super.initState();
    workingHours = List.of(allHours);
    projectController = TextEditingController();
    hoursController = TextEditingController();
    dateController = TextEditingController();
  }

  @override
  void dispose(){
    projectController.dispose();
    hoursController.dispose();
    dateController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final postKey = database.child('users/$userId/workinghours/').push().key;
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
                          final newEntry = await addNewHoursEntry();
                          final newEntry2 = newEntry?.toJson();
                          updates['/users/$userId/workinghours/$postKey'] = newEntry2!;

                          setState(() => [
                            workingHours.add(newEntry!),
                            database.update(updates),
                          ]);
                        }, child: const Text('Add entry')
                    )),
                    Expanded(child: ElevatedButton(
                        onPressed: () {
                          setState(() {
                            updateHours();
                          });
                        },
                        child: const Text('Update Table')
                    ))
                  ]))
            ])
      ),
      appBar: AppBar(
        title: const Text('Working Hours Overview'),
        centerTitle: true,
        backgroundColor: Colors.green,
      ),
    );
  }

  Widget buildDataTable() {
    final columns = ['Project', 'Amount', 'Date', 'Status'];
    return DataTable(
      columns: getColumns(columns),
      rows: getRows(workingHours),
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

  List<DataRow> getRows(List<Hours> workingHours) => workingHours.map((Hours hours) {
    final cells = [hours.project, hours.amount, hours.date, hours.status];
    return DataRow(
      cells: Utils.modelBuilder(cells, (index, cell) {
        final showEditIcon = index == 3;
        return DataCell(
          Text('$cell'),
          showEditIcon: showEditIcon,
          onTap: () {
            switch (index) {
              case 3:
                editStatus(hours);
                break;
            }
          },
        );
      }),
    );
  }).toList();

  Future editStatus(Hours editHours) async {
    final status = await showTextDialog(
      context,
      title: 'Change Status',
      value: editHours.status,
    );

    setState(() => workingHours = workingHours.map((hours) {
      final isEditedHours = hours == editHours;

      return isEditedHours ? hours.copy(status: status) : hours;
    }).toList());
  }

  Future<Hours?> addNewHoursEntry() => showDialog<Hours>(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('New Hours Entry'),
        content: SingleChildScrollView(
          child: SizedBox(
            width: double.infinity,
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                TextField(
                  autofocus: true,
                  decoration: const InputDecoration(hintText: 'Project'),
                  controller: projectController,
                ),
                TextField(
                  decoration: const InputDecoration(hintText: 'Hours'),
                  controller: hoursController,
                ),
                TextField(
                  decoration: const InputDecoration(hintText: 'Date (DD-MM-YYYY)'),
                  controller: dateController,
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
    final workingHour = Hours(project: projectController.text, amount: int.parse(hoursController.text), date: dateController.text, status: 'Submitted');
    Navigator.of(context).pop(workingHour);
  }

  Future<void> updateHours() async{
    final snapshot =  database.child('users/$userId/workinghours/');
    snapshot.onValue.listen((DatabaseEvent event) {
      for (final child in event.snapshot.children){
        var dbHours = Hours.fromJson(child.value as Map<dynamic, dynamic>);
        workingHours.add(dbHours);
      }
    });
  }
}
