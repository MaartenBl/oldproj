import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_database/firebase_database.dart';
import 'package:flutter/material.dart';
import 'package:frontend/widget/scrollable_widget.dart';
import '../model/hours.dart';
import '../model/mileage.dart';
import '../utils.dart';
import '../widget/navigation_drawer_widget.dart';

class StartPage extends StatefulWidget {
  const StartPage({Key? key}) : super(key: key);

  @override
  _StartPageState createState() => _StartPageState();
}

class _StartPageState extends State<StartPage>{
  final database = FirebaseDatabase.instance.ref();
  final userId = FirebaseAuth.instance.currentUser!.uid;
  final user = FirebaseAuth.instance.currentUser!;
  String buttonText = '';
  late List<Hours> workingEntries = [];
  late List<Mileage> mileageEntries = [];

  @override
  void initState(){
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: const NavigationDrawerWidget(),
      appBar: AppBar(
        title: const Text('Latest entries'),
        centerTitle: true,
        backgroundColor: Colors.green,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            const Text(
              'Welcome Back:',
              style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold),
              textAlign: TextAlign.center,
            ),
            const SizedBox(height: 8),
            Text(
              user.email!,
              style: const TextStyle(fontSize: 20),
            ),
            Text(
              buttonText,
              style: const TextStyle(fontSize: 20),
            ),
            const Divider(color: Colors.black26),
            const SizedBox(height: 8),
            const Text(
              'Overview Hours - Latest Entry',
              style: TextStyle(fontSize: 16),
            ),
            ScrollableWidget(
              child: buildHoursDataTable(),
            ),
            const SizedBox(height: 8),
            const Text(
              'Overview Mileage - Latest Entry',
              style: TextStyle(fontSize: 16),
            ),
            ScrollableWidget(
              child: buildMileageDataTable(),
            ),
            const Divider(color: Colors.black26),
            ElevatedButton(onPressed: () {
              setState(() {
                updateUserInfo();
              });
            }, child: const Text('Update')),
          ],
        ),
      ),
    );
  }

  Widget buildHoursDataTable() {
    final columns = ['Project', 'Amount', 'Date', 'Status'];
    return DataTable(
      columns: getColumns(columns),
      rows: getHourRows(workingEntries),
      border: TableBorder.symmetric(
          inside: const BorderSide(
            width: 1,
          ),
          outside: const BorderSide(
            width: 1,
          )
      ),
    );
  }
  List<DataRow> getHourRows(List<Hours> workingHours) => workingHours.map((Hours hours) {
    final cells = [hours.project, hours.amount, hours.date, hours.status];
    return DataRow(
      cells: Utils.modelBuilder(cells, (index, cell) {
        return DataCell(
          Text('$cell'),
        );
      }),
    );
  }).toList();
  List<DataColumn> getColumns(List<String> columns) {
    return columns.map((String column) {
      return DataColumn(
        label: Text(column),
      );
    }).toList();
  }
  Widget buildMileageDataTable() {
    final columns = ['Name', 'Date', 'Start Address', 'End Address', 'Distance (km)', 'Status'];
    return DataTable(
      columns: getColumns(columns),
      rows: getMileageRows(mileageEntries),
      border: TableBorder.symmetric(
          inside: const BorderSide(
            width: 1,
          ),
          outside: const BorderSide(
            width: 1,
          )
      ),
    );
  }

  List<DataRow> getMileageRows(List<Mileage> mileageList) => mileageList.map((Mileage mileage) {
    final cells = [mileage.name, mileage.date, mileage.startAddress, mileage.endAddress, mileage.distance, mileage.status];
    return DataRow(
      cells: Utils.modelBuilder(cells, (index, cell) {
        return DataCell(
          Text('$cell'),
        );
      }),
    );
  }).toList();

  Future<void> updateUserInfo() async{
    workingEntries.clear();
    final dbWorkSnapshot =  database.child('users/$userId/workinghours/').limitToLast(3);
    dbWorkSnapshot.onValue.listen((DatabaseEvent event) {
      for (final child in event.snapshot.children){
        var dbHours = Hours.fromJson(child.value as Map<dynamic, dynamic>);
        workingEntries.add(dbHours);
      }
    });
    final dbUserInfo = await database.child('users/$userId').child('displayname').get();
    buttonText = dbUserInfo.value.toString();
    mileageEntries.clear();
    final dbMileageSnapshot = database.child('users/$userId/mileage/').limitToLast(3);
    dbMileageSnapshot.onValue.listen((DatabaseEvent event) {
      for (final child in event.snapshot.children){
        print(child.value);
        var dbMileage = Mileage.fromJson(child.value as Map<dynamic, dynamic>);
        mileageEntries.add(dbMileage);
      }
    });
  }
}