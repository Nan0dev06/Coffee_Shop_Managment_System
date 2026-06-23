import React from 'react';

export function DataTable({
  columns = [],
  rows = [],
  style: styleProp,
  renderCell,
  ...props
}) {
  const tableStyle = {
    fontFamily: 'var(--font-family)',
    width: '100%',
    borderCollapse: 'separate',
    borderSpacing: 0,
    border: '1px solid var(--border-light)',
    borderRadius: 'var(--radius-lg)',
    overflow: 'hidden',
    ...styleProp,
  };

  const thStyle = {
    background: 'var(--beige-100)',
    fontSize: 'var(--font-size-caption)',
    fontWeight: 'var(--font-weight-semibold)',
    color: 'var(--text-primary)',
    padding: 'var(--space-8) var(--space-12)',
    textAlign: 'left',
    borderBottom: '1px solid var(--border-light)',
    letterSpacing: 'var(--letter-spacing-wide)',
    textTransform: 'uppercase',
  };

  const getCellStyle = (rowIdx) => ({
    fontSize: 'var(--font-size-body)',
    fontWeight: 'var(--font-weight-regular)',
    color: 'var(--text-primary)',
    padding: 'var(--space-8) var(--space-12)',
    background: rowIdx % 2 === 0 ? 'var(--surface-card)' : 'var(--surface-page)',
    borderBottom: '1px solid var(--border-light)',
    minHeight: 'var(--row-min-height)',
    verticalAlign: 'middle',
  });

  return React.createElement('table', { style: tableStyle, ...props },
    React.createElement('thead', null,
      React.createElement('tr', null,
        columns.map((col, ci) =>
          React.createElement('th', {
            key: ci,
            style: { ...thStyle, width: col.width, textAlign: col.align || 'left' },
          }, col.label)
        )
      )
    ),
    React.createElement('tbody', null,
      rows.map((row, ri) =>
        React.createElement('tr', { key: ri },
          columns.map((col, ci) =>
            React.createElement('td', {
              key: ci,
              style: { ...getCellStyle(ri), textAlign: col.align || 'left' },
            },
              renderCell ? renderCell(row, col, ri, ci) : row[col.key]
            )
          )
        )
      )
    )
  );
}
