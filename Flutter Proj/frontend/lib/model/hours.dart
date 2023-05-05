class Hours {
  late String project;
  late int amount ;
  late String date;
  late String status;

  Hours({
    required this.project,
    required this.amount,
    required this.date,
    required this.status,
  });

  Hours copy({
    String? project,
    int? amount,
    String? date,
    String? status,
  }) =>
      Hours(
        project: project ?? this.project,
        amount: amount ?? this.amount,
        date: date ?? this.date,
        status: status ?? this.status,
      );

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
          other is Hours &&
              runtimeType == other.runtimeType &&
              project == other.project &&
              amount == other.amount &&
              date == other.date &&
              status == other.status;

  @override
  int get hashCode => project.hashCode ^ amount.hashCode ^ date.hashCode ^ status.hashCode;

  @override
  String toString() {
    return '{project: $project, amount: $amount, date: $date, status: $status}';
  }

  Map<String, dynamic> toJson() {
    return {
      if (project != null) "project": project,
      if (amount != null) "amount": amount,
      if (date != null) "date": date,
      if (status != null) "status": status
    };
  }

  Hours.fromJson(Map json){
    project = json['project'];
    amount = json['amount'];
    date = json['date'];
    status = json['status'];
  }
}
