class Projects {
  final String type;
  final String startDate;
  final String endDate;
  final String status;

  const Projects({
    required this.type,
    required this.startDate,
    required this.endDate,
    required this.status,
  });

  Projects copy({
    String? type,
    String? startDate,
    String? endDate,
    String? status,
  }) =>
      Projects(
        type: type ?? this.type,
        startDate: startDate ?? this.startDate,
        endDate: endDate ?? this.endDate,
        status: status ?? this.status,
      );

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
          other is Projects &&
              runtimeType == other.runtimeType &&
              type == other.type &&
              startDate == other.startDate &&
              endDate == other.endDate &&
              status == other.status;

  @override
  int get hashCode => type.hashCode ^ startDate.hashCode ^ endDate.hashCode ^ status.hashCode;
}
