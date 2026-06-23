import React from 'react';

export function StatCard({
  label,
  value,
  trend,
  trendLabel,
  showAccent = true,
  style: styleProp,
  ...props
}) {
  const cardStyle = {
    fontFamily: 'var(--font-family)',
    background: 'var(--surface-card)',
    border: '1px solid var(--border-light)',
    borderRadius: 'var(--radius-lg)',
    padding: 0,
    display: 'flex',
    overflow: 'hidden',
    minWidth: 'var(--card-min-width)',
    ...styleProp,
  };

  const accentStyle = {
    width: '4px',
    flexShrink: 0,
    background: 'linear-gradient(to bottom, var(--brown-600), var(--brown-400))',
  };

  const contentStyle = {
    padding: 'var(--space-24)',
    display: 'flex',
    flexDirection: 'column',
    gap: 'var(--space-4)',
    flex: 1,
  };

  const labelStyle = {
    fontSize: 'var(--font-size-label)',
    fontWeight: 'var(--font-weight-medium)',
    color: 'var(--text-secondary)',
    margin: 0,
    lineHeight: 'var(--line-height-normal)',
  };

  const valueStyle = {
    fontSize: 'var(--font-size-heading)',
    fontWeight: 'var(--font-weight-bold)',
    color: 'var(--text-primary)',
    margin: 0,
    lineHeight: 'var(--line-height-tight)',
  };

  const trendStyle = {
    fontSize: 'var(--font-size-caption)',
    fontWeight: 'var(--font-weight-medium)',
    color: trend === 'up' ? 'var(--success)' : trend === 'down' ? 'var(--warning)' : 'var(--text-secondary)',
    margin: 0,
    marginTop: 'var(--space-4)',
  };

  return React.createElement('div', { style: cardStyle, ...props },
    showAccent && React.createElement('div', { style: accentStyle }),
    React.createElement('div', { style: contentStyle },
      React.createElement('p', { style: labelStyle }, label),
      React.createElement('p', { style: valueStyle }, value),
      trendLabel && React.createElement('p', { style: trendStyle },
        trend === 'up' ? '▲ ' : trend === 'down' ? '▼ ' : '',
        trendLabel
      )
    )
  );
}
