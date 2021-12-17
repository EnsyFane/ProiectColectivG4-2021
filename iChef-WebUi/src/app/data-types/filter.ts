export interface Filter {
    field: string;
    operation: string;
    text: string;
}

export interface FilterCriteria {
    filters: Filter[];
}
