/**
 * Pill badge for status indicators — primarily the low-stock warning in Inventory.
 *
 * @startingPoint section="Components" subtitle="Warning, success, and neutral pill badges" viewport="700x100"
 */
export interface BadgeProps {
  children: React.ReactNode;
  /** Color variant */
  variant?: 'warning' | 'success' | 'neutral';
  style?: React.CSSProperties;
}

export function Badge(props: BadgeProps): JSX.Element;
