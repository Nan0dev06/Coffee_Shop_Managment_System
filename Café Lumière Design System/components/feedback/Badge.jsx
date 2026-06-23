import React from 'react';

export function Badge({
  children,
  variant = 'warning',
  style: styleProp,
  ...props
}) {
  const variants = {
    warning: {
      background: 'var(--warning-bg)',
      color: 'var(--warning-text)',
    },
    success: {
      background: 'var(--success-bg)',
      color: 'var(--success)',
    },
    neutral: {
      background: 'var(--beige-100)',
      color: 'var(--text-secondary)',
    },
  };

  const v = variants[variant] || variants.warning;

  const badgeStyle = {
    fontFamily: 'var(--font-family)',
    fontSize: 'var(--font-size-caption)',
    fontWeight: 'var(--font-weight-semibold)',
    lineHeight: 'var(--line-height-tight)',
    background: v.background,
    color: v.color,
    padding: '4px 12px',
    borderRadius: 'var(--radius-round)',
    display: 'inline-flex',
    alignItems: 'center',
    gap: 'var(--space-4)',
    whiteSpace: 'nowrap',
    ...styleProp,
  };

  return React.createElement('span', { style: badgeStyle, ...props }, children);
}
