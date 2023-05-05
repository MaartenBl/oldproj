class Address {
  late String name;
  late String destination;
  late String description;

  Address({
    required this.name,
    required this.destination,
    required this.description,
  });

  Address copy({
    String? name,
    String? destination,
    String? description,
  }) =>
      Address(
        name: name ?? this.name,
        destination: destination ?? this.destination,
        description: description ?? this.description,
      );

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
          other is Address &&
              runtimeType == other.runtimeType &&
              name == other.name &&
              destination == other.destination &&
              description == other.description;

  @override
  int get hashCode => name.hashCode ^ destination.hashCode ^ description.hashCode;

  @override
  String toString(){
    return '{name: $name, destination: $destination, description: $description}';
  }

  Map<String, dynamic> toJson(){
    return{
      "name":name,
      "destination":destination,
      "description":description
    };
  }

  Address.fromJson(Map json){
    name = json['name'];
    destination = json['destination'];
    description = json['description'];
  }
}
