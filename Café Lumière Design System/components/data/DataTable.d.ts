/**
 * Simple data table with alternating row colors — for Inventory and Recent Orders lists.
 * No pagination, no sorting — static-feeling table per the app spec.
 *
 * @startingPoint section="Components" subtitle="Alternating-row data table" viewport="700x260"
 */
export interface Column {
  key: string;
  label: string;
  width?: string;
  align?: 'left' | 'center' | 'right';
}

export interface DataTableProps {
  /** Column definitions */
  columns: Column[];
  /** Row data — each row is an object keyed by column key */
  rows: Record<string, any>[];
  /** Custom cell renderer (row, column, rowIndex, colIndex) => ReactNode */
  renderCell?: (row: Record<string, any>, col: Column, rowIndex: number, colIndex: number) => React.ReactNode;
  style?: React.CSSProperties;
}

export function DataTable(props: DataTableProps): JSX.Element;
