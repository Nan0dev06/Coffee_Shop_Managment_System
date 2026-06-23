/**
 * Dashboard stat card — shows a KPI label, large value, and optional trend.
 * Used on Dashboard and Revenue Summary screens.
 *
 * @startingPoint section="Components" subtitle="KPI stat card with accent strip" viewport="700x160"
 */
export interface StatCardProps {
  /** Metric label (e.g. "Total Revenue") */
  label: string;
  /** Displayed value (e.g. "$12,450") */
  value: string;
  /** Trend direction */
  trend?: 'up' | 'down' | 'neutral';
  /** Trend description (e.g. "+12% from last month") */
  trendLabel?: string;
  /** Show left accent gradient strip */
  showAccent?: boolean;
  style?: React.CSSProperties;
}

export function StatCard(props: StatCardProps): JSX.Element;
