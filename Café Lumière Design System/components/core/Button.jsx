import React from 'react';

export function Button({
  children,
  variant = 'primary',
  size = 'md',
  disabled = false,
  fullWidth = false,
  onClick,
  style: styleProp,
  ...props
}) {
  const [hover, setHover] = React.useState(false);
  const [pressed, setPressed] = React.useState(false);

  const v = {
    primary: {
      bg: 'linear-gradient(to right, var(--btn-primary-start), var(--btn-primary-end))',
      color: 'var(--btn-primary-text)',
      hoverBg: 'var(--btn-primary-hover)',
      pressedBg: 'var(--btn-primary-pressed)',
    },
    secondary: {
      bg: 'var(--btn-secondary-bg)',
      color: 'var(--btn-secondary-text)',
      hoverBg: 'var(--btn-secondary-hover)',
      pressedBg: 'var(--btn-secondary-pressed)',
    },
    danger: {
      bg: 'var(--warning)',
      color: '#FFFFFF',
      hoverBg: '#9A3B22',
      pressedBg: '#7A2E1A',
    },
  }[variant] || v?.primary;

  const sizes = {
    sm: { padding: '6px 16px', fontSize: 'var(--font-size-caption)' },
    md: { padding: '8px 24px', fontSize: 'var(--font-size-label)' },
    lg: { padding: '12px 32px', fontSize: 'var(--font-size-body)' },
  };

  const bg = pressed ? v.pressedBg : hover ? v.hoverBg : v.bg;
  const isGradient = bg.includes('gradient');

  const baseStyle = {
    fontFamily: 'var(--font-family)',
    fontWeight: 'var(--font-weight-semibold)',
    fontSize: sizes[size]?.fontSize || sizes.md.fontSize,
    padding: sizes[size]?.padding || sizes.md.padding,
    color: v.color,
    background: isGradient ? bg : bg,
    border: 'none',
    borderRadius: 'var(--radius-md)',
    cursor: disabled ? 'default' : 'pointer',
    opacity: disabled ? 0.5 : 1,
    width: fullWidth ? '100%' : 'auto',
    display: 'inline-flex',
    alignItems: 'center',
    justifyContent: 'center',
    gap: 'var(--space-8)',
    lineHeight: 'var(--line-height-tight)',
    transition: 'none',
    ...styleProp,
  };

  return React.createElement('button', {
    style: baseStyle,
    disabled,
    onClick,
    onMouseEnter: () => !disabled && setHover(true),
    onMouseLeave: () => { setHover(false); setPressed(false); },
    onMouseDown: () => !disabled && setPressed(true),
    onMouseUp: () => setPressed(false),
    ...props,
  }, children);
}
