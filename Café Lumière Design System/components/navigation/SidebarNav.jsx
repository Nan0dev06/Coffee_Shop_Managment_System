import React from 'react';

export function SidebarNav({
  items = [],
  activeId,
  onSelect,
  brandName = 'Café Lumière',
  style: styleProp,
  ...props
}) {
  const [hoveredId, setHoveredId] = React.useState(null);

  const sidebarStyle = {
    fontFamily: 'var(--font-family)',
    width: 'var(--sidebar-width)',
    minHeight: '100%',
    background: 'linear-gradient(to bottom, #4E2E1E, #5C3A27)',
    padding: 'var(--space-16) 0',
    display: 'flex',
    flexDirection: 'column',
    flexShrink: 0,
    boxSizing: 'border-box',
    ...styleProp,
  };

  const brandStyle = {
    fontSize: 'var(--font-size-heading)',
    fontWeight: 'var(--font-weight-bold)',
    color: 'var(--text-on-dark)',
    margin: 0,
    padding: '0 var(--space-24)',
    marginBottom: 'var(--space-32)',
    lineHeight: 'var(--line-height-tight)',
  };

  const navStyle = {
    display: 'flex',
    flexDirection: 'column',
    gap: 'var(--space-4)',
    padding: '0 var(--space-12)',
    flex: 1,
  };

  const getItemStyle = (item) => {
    const isActive = item.id === activeId;
    const isHover = item.id === hoveredId && !isActive;
    const isLogout = item.id === 'logout';
    return {
      display: 'flex',
      alignItems: 'center',
      gap: 'var(--space-8)',
      padding: '0 var(--space-16)',
      height: '44px',
      borderRadius: 'var(--radius-md)',
      background: isActive ? 'var(--brown-600)'
        : isHover && isLogout ? '#7A2E1A'
        : isHover ? '#5C3A27'
        : 'transparent',
      color: isActive ? 'var(--white)'
        : isHover ? 'var(--text-on-dark)'
        : 'var(--beige-300)',
      cursor: 'pointer',
      fontSize: 'var(--font-size-label)',
      fontWeight: isActive ? 'var(--font-weight-semibold)' : 'var(--font-weight-medium)',
      fontFamily: 'var(--font-family)',
      border: 'none',
      textAlign: 'left',
      width: '100%',
      boxSizing: 'border-box',
    };
  };

  return React.createElement('nav', { style: sidebarStyle, ...props },
    React.createElement('h1', { style: brandStyle }, brandName),
    React.createElement('div', { style: navStyle },
      items.filter(i => i.id !== 'logout').map(item =>
        React.createElement('button', {
          key: item.id,
          style: getItemStyle(item),
          onClick: () => onSelect && onSelect(item.id),
          onMouseEnter: () => setHoveredId(item.id),
          onMouseLeave: () => setHoveredId(null),
        },
          item.icon && React.createElement('span', {
            style: { fontSize: '18px', width: '20px', textAlign: 'center', flexShrink: 0 },
          }, item.icon),
          item.label
        )
      ),
    ),
    items.find(i => i.id === 'logout') && React.createElement('div', {
      style: { padding: '0 var(--space-12)', marginTop: 'auto' },
    },
      React.createElement('button', {
        style: getItemStyle(items.find(i => i.id === 'logout')),
        onClick: () => onSelect && onSelect('logout'),
        onMouseEnter: () => setHoveredId('logout'),
        onMouseLeave: () => setHoveredId(null),
      },
        items.find(i => i.id === 'logout').icon && React.createElement('span', {
          style: { fontSize: '18px', width: '20px', textAlign: 'center', flexShrink: 0 },
        }, items.find(i => i.id === 'logout').icon),
        items.find(i => i.id === 'logout').label
      )
    )
  );
}
