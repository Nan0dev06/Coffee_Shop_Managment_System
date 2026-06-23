/**
 * Primary action button — maps to KButton in Java Swing.
 * Supports gradient fills, hover/pressed color states, three variants.
 *
 * @startingPoint section="Components" subtitle="Primary, secondary, and danger buttons" viewport="700x200"
 */
export interface ButtonProps {
  children: React.ReactNode;
  /** Visual variant */
  variant?: 'primary' | 'secondary' | 'danger';
  /** Size preset */
  size?: 'sm' | 'md' | 'lg';
  /** Disabled state — 50% opacity, no interactions */
  disabled?: boolean;
  /** Stretch to fill container width */
  fullWidth?: boolean;
  /** Click handler */
  onClick?: () => void;
  /** Override styles */
  style?: React.CSSProperties;
}

export function Button(props: ButtonProps): JSX.Element;
