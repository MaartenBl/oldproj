import 'package:frontend/model/mileage.dart';
import 'package:frontend/model/address.dart';
import 'package:intl/intl.dart';

final today = DateFormat('dd-MM-yyyy').format(DateTime.now());
final yesterday = DateFormat('dd-MM-yyyy').format(DateTime.now().subtract(const Duration(days: 1)));
final ereYesterday = DateFormat('dd-MM-yyyy').format(DateTime.now().subtract(const Duration(days: 2)));

Address homeAddress = Address(name: 'Home', destination: 'Homestreet 123', description: 'Home address');
Address workAddress = Address(name: 'Work', destination: 'Workstreet 456', description: 'Work address');
Address clientAddress = Address(name: 'Client X', destination: 'Clientstreet 789', description: 'Client X Address');

final allMileage = <Mileage>[
  Mileage(name: 'From home to work', date: ereYesterday, startAddress: 'Home', endAddress: 'Work', distance: 50, status: 'Submitted'),
  Mileage(name: 'From work to home', date: yesterday, startAddress: 'Work', endAddress: 'Home', distance: 50, status: 'Submitted'),
  Mileage(name: 'From home to client X', date: ereYesterday, startAddress: 'Home', endAddress: 'Client x', distance: 110, status: 'Submitted'),
];