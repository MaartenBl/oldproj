class Mileage {
  late String name;
  late String date;
  late String startAddress;
  late String endAddress;
  late int distance;
  late String status;

  Mileage({
    required this.name,
    required this.date,
    required this.startAddress,
    required this.endAddress,
    required this.distance,
    required this.status,
  });

  Mileage copy({
    String? name,
    String? date,
    String? startAddress,
    String? endAddress,
    int? distance,
    String? status,
  }) =>
      Mileage(
        name: name ?? this.name,
        date: date ?? this.date,
        startAddress: startAddress ?? this.startAddress,
        endAddress: endAddress ?? this.endAddress,
        distance: distance ?? this.distance,
        status: status ?? this.status,
      );

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
          other is Mileage &&
              runtimeType == other.runtimeType &&
              name == other.name &&
              date == other.date &&
              startAddress == other.startAddress &&
              endAddress == other.endAddress &&
              distance == other.distance &&
              status == other.status;

  @override
  int get hashCode => name.hashCode ^ date.hashCode ^ startAddress.hashCode ^ endAddress.hashCode ^ distance.hashCode ^ status.hashCode;

  @override
  String toString(){
    return '{name: $name, date: $date, startAddress: $startAddress, endAddress: $endAddress, distance: $distance, status: $status}';
  }

  Map<String, dynamic> toJson(){
    return{
      "name":name,
      "date":date,
      "startAddress":startAddress,
      "endAddress":endAddress,
      "distance":distance,
      "status":status
    };
  }

  Mileage.fromJson(Map json){
    name = json['name'];
    date = json['date'];
    startAddress = json['startAddress'];
    endAddress = json['endAddress'];
    distance = json['distance'];
    status = json['status'];
  }
}
