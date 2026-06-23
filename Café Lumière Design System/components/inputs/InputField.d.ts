/**
 * Text input with label and validation error state. Used in Login and Order Entry screens.
 *
 * @startingPoint section="Components" subtitle="Labeled text input with focus and error states" viewport="700x160"
 */
export interface InputFieldProps {
  /** Label text above the input */
  label?: string;
  /** Input type */
  type?: 'text' | 'password' | 'number' | 'email';
  placeholder?: string;
  value?: string;
  onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
  disabled?: boolean;
  /** Error message shown below input */
  error?: string;
  style?: React.CSSProperties;
}

export function InputField(props: InputFieldProps): JSX.Element;
