import React from 'react';

export function InputField({
  label,
  type = 'text',
  placeholder,
  value,
  onChange,
  disabled = false,
  error,
  style: styleProp,
  ...props
}) {
  const [focused, setFocused] = React.useState(false);

  const wrapStyle = {
    fontFamily: 'var(--font-family)',
    display: 'flex',
    flexDirection: 'column',
    gap: 'var(--space-4)',
    ...styleProp,
  };

  const labelStyle = {
    fontSize: 'var(--font-size-label)',
    fontWeight: 'var(--font-weight-medium)',
    color: 'var(--text-primary)',
  };

  const inputStyle = {
    fontFamily: 'var(--font-family)',
    fontSize: 'var(--font-size-body)',
    fontWeight: 'var(--font-weight-regular)',
    color: 'var(--text-primary)',
    background: 'var(--surface-input)',
    border: focused
      ? '2px solid var(--brown-600)'
      : error
        ? '2px solid var(--warning)'
        : '1px solid var(--border-medium)',
    borderRadius: 'var(--radius-md)',
    padding: '10px 14px',
    outline: 'none',
    opacity: disabled ? 0.5 : 1,
    width: '100%',
    boxSizing: 'border-box',
  };

  const errorStyle = {
    fontSize: 'var(--font-size-caption)',
    fontWeight: 'var(--font-weight-medium)',
    color: 'var(--warning)',
    margin: 0,
  };

  return React.createElement('div', { style: wrapStyle },
    label && React.createElement('label', { style: labelStyle }, label),
    React.createElement('input', {
      type,
      placeholder,
      value,
      onChange,
      disabled,
      style: inputStyle,
      onFocus: () => setFocused(true),
      onBlur: () => setFocused(false),
      ...props,
    }),
    error && React.createElement('p', { style: errorStyle }, error)
  );
}
