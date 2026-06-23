/* @ds-bundle: {"format":3,"namespace":"CafLumiReDesignSystem_77c5a3","components":[{"name":"StatCard","sourcePath":"components/cards/StatCard.jsx"},{"name":"Button","sourcePath":"components/core/Button.jsx"},{"name":"DataTable","sourcePath":"components/data/DataTable.jsx"},{"name":"DrinkCard","sourcePath":"components/drinks/DrinkCard.jsx"},{"name":"Badge","sourcePath":"components/feedback/Badge.jsx"},{"name":"InputField","sourcePath":"components/inputs/InputField.jsx"},{"name":"SidebarNav","sourcePath":"components/navigation/SidebarNav.jsx"}],"sourceHashes":{"components/cards/StatCard.jsx":"4577cc26ecb9","components/core/Button.jsx":"7a2628d98c68","components/data/DataTable.jsx":"61cc26b6573b","components/drinks/DrinkCard.jsx":"bedff8c109f5","components/feedback/Badge.jsx":"ddf491dd2b3f","components/inputs/InputField.jsx":"ed3dc37eae8f","components/navigation/SidebarNav.jsx":"911fd6ed9eed","ui_kits/desktop_app/DashboardScreen.jsx":"0daf6259ee25","ui_kits/desktop_app/InventoryScreen.jsx":"d1160b9ad762","ui_kits/desktop_app/LoginScreen.jsx":"bf93b92c5a2d","ui_kits/desktop_app/OrderEntryScreen.jsx":"903a6f0656d8","ui_kits/desktop_app/RevenueScreen.jsx":"7066bb1d69c9"},"inlinedExternals":[],"unexposedExports":[]} */

(() => {

const __ds_ns = (window.CafLumiReDesignSystem_77c5a3 = window.CafLumiReDesignSystem_77c5a3 || {});

const __ds_scope = {};

(__ds_ns.__errors = __ds_ns.__errors || []);

// components/cards/StatCard.jsx
try { (() => {
function StatCard({
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
    ...styleProp
  };
  const accentStyle = {
    width: '4px',
    flexShrink: 0,
    background: 'linear-gradient(to bottom, var(--brown-600), var(--brown-400))'
  };
  const contentStyle = {
    padding: 'var(--space-24)',
    display: 'flex',
    flexDirection: 'column',
    gap: 'var(--space-4)',
    flex: 1
  };
  const labelStyle = {
    fontSize: 'var(--font-size-label)',
    fontWeight: 'var(--font-weight-medium)',
    color: 'var(--text-secondary)',
    margin: 0,
    lineHeight: 'var(--line-height-normal)'
  };
  const valueStyle = {
    fontSize: 'var(--font-size-heading)',
    fontWeight: 'var(--font-weight-bold)',
    color: 'var(--text-primary)',
    margin: 0,
    lineHeight: 'var(--line-height-tight)'
  };
  const trendStyle = {
    fontSize: 'var(--font-size-caption)',
    fontWeight: 'var(--font-weight-medium)',
    color: trend === 'up' ? 'var(--success)' : trend === 'down' ? 'var(--warning)' : 'var(--text-secondary)',
    margin: 0,
    marginTop: 'var(--space-4)'
  };
  return React.createElement('div', {
    style: cardStyle,
    ...props
  }, showAccent && React.createElement('div', {
    style: accentStyle
  }), React.createElement('div', {
    style: contentStyle
  }, React.createElement('p', {
    style: labelStyle
  }, label), React.createElement('p', {
    style: valueStyle
  }, value), trendLabel && React.createElement('p', {
    style: trendStyle
  }, trend === 'up' ? '▲ ' : trend === 'down' ? '▼ ' : '', trendLabel)));
}
Object.assign(__ds_scope, { StatCard });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/cards/StatCard.jsx", error: String((e && e.message) || e) }); }

// components/core/Button.jsx
try { (() => {
function Button({
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
      pressedBg: 'var(--btn-primary-pressed)'
    },
    secondary: {
      bg: 'var(--btn-secondary-bg)',
      color: 'var(--btn-secondary-text)',
      hoverBg: 'var(--btn-secondary-hover)',
      pressedBg: 'var(--btn-secondary-pressed)'
    },
    danger: {
      bg: 'var(--warning)',
      color: '#FFFFFF',
      hoverBg: '#9A3B22',
      pressedBg: '#7A2E1A'
    }
  }[variant] || v?.primary;
  const sizes = {
    sm: {
      padding: '6px 16px',
      fontSize: 'var(--font-size-caption)'
    },
    md: {
      padding: '8px 24px',
      fontSize: 'var(--font-size-label)'
    },
    lg: {
      padding: '12px 32px',
      fontSize: 'var(--font-size-body)'
    }
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
    ...styleProp
  };
  return React.createElement('button', {
    style: baseStyle,
    disabled,
    onClick,
    onMouseEnter: () => !disabled && setHover(true),
    onMouseLeave: () => {
      setHover(false);
      setPressed(false);
    },
    onMouseDown: () => !disabled && setPressed(true),
    onMouseUp: () => setPressed(false),
    ...props
  }, children);
}
Object.assign(__ds_scope, { Button });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/core/Button.jsx", error: String((e && e.message) || e) }); }

// components/data/DataTable.jsx
try { (() => {
function DataTable({
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
    ...styleProp
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
    textTransform: 'uppercase'
  };
  const getCellStyle = rowIdx => ({
    fontSize: 'var(--font-size-body)',
    fontWeight: 'var(--font-weight-regular)',
    color: 'var(--text-primary)',
    padding: 'var(--space-8) var(--space-12)',
    background: rowIdx % 2 === 0 ? 'var(--surface-card)' : 'var(--surface-page)',
    borderBottom: '1px solid var(--border-light)',
    minHeight: 'var(--row-min-height)',
    verticalAlign: 'middle'
  });
  return React.createElement('table', {
    style: tableStyle,
    ...props
  }, React.createElement('thead', null, React.createElement('tr', null, columns.map((col, ci) => React.createElement('th', {
    key: ci,
    style: {
      ...thStyle,
      width: col.width,
      textAlign: col.align || 'left'
    }
  }, col.label)))), React.createElement('tbody', null, rows.map((row, ri) => React.createElement('tr', {
    key: ri
  }, columns.map((col, ci) => React.createElement('td', {
    key: ci,
    style: {
      ...getCellStyle(ri),
      textAlign: col.align || 'left'
    }
  }, renderCell ? renderCell(row, col, ri, ci) : row[col.key]))))));
}
Object.assign(__ds_scope, { DataTable });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/data/DataTable.jsx", error: String((e && e.message) || e) }); }

// components/drinks/DrinkCard.jsx
try { (() => {
function DrinkCard({
  name,
  price,
  imageUrl,
  imagePlaceholder,
  onAdd,
  style: styleProp,
  ...props
}) {
  const [hover, setHover] = React.useState(false);
  const [pressed, setPressed] = React.useState(false);
  const cardStyle = {
    fontFamily: 'var(--font-family)',
    background: 'var(--surface-card)',
    border: '1px solid var(--border-light)',
    borderRadius: 'var(--radius-lg)',
    padding: 'var(--space-16)',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    gap: 'var(--space-8)',
    width: '180px',
    boxSizing: 'border-box',
    ...styleProp
  };
  const imgWrap = {
    width: '120px',
    height: '120px',
    borderRadius: 'var(--radius-round)',
    background: 'var(--beige-100)',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    overflow: 'hidden',
    flexShrink: 0
  };
  const imgStyle = {
    width: '100%',
    height: '100%',
    objectFit: 'cover',
    borderRadius: 'var(--radius-round)'
  };
  const placeholderStyle = {
    fontSize: '36px',
    fontWeight: 'var(--font-weight-bold)',
    color: 'var(--brown-400)'
  };
  const nameStyle = {
    fontSize: 'var(--font-size-subheading)',
    fontWeight: 'var(--font-weight-semibold)',
    color: 'var(--text-primary)',
    margin: 0,
    textAlign: 'center',
    lineHeight: 'var(--line-height-tight)'
  };
  const priceStyle = {
    fontSize: 'var(--font-size-label)',
    fontWeight: 'var(--font-weight-regular)',
    color: 'var(--text-secondary)',
    margin: 0
  };
  const btnBg = pressed ? 'var(--btn-primary-pressed)' : hover ? 'var(--btn-primary-hover)' : 'linear-gradient(to right, var(--btn-primary-start), var(--btn-primary-end))';
  const btnStyle = {
    fontFamily: 'var(--font-family)',
    fontSize: 'var(--font-size-label)',
    fontWeight: 'var(--font-weight-semibold)',
    color: 'var(--btn-primary-text)',
    background: btnBg,
    border: 'none',
    borderRadius: 'var(--radius-md)',
    padding: '8px 0',
    cursor: 'pointer',
    width: '100%',
    marginTop: 'var(--space-4)'
  };
  return React.createElement('div', {
    style: cardStyle,
    ...props
  }, React.createElement('div', {
    style: imgWrap
  }, imageUrl ? React.createElement('img', {
    src: imageUrl,
    alt: name,
    style: imgStyle
  }) : React.createElement('span', {
    style: placeholderStyle
  }, imagePlaceholder || (name ? name[0] : '?'))), React.createElement('p', {
    style: nameStyle
  }, name), React.createElement('p', {
    style: priceStyle
  }, price), React.createElement('button', {
    style: btnStyle,
    onClick: onAdd,
    onMouseEnter: () => setHover(true),
    onMouseLeave: () => {
      setHover(false);
      setPressed(false);
    },
    onMouseDown: () => setPressed(true),
    onMouseUp: () => setPressed(false)
  }, 'Add to Cart'));
}
Object.assign(__ds_scope, { DrinkCard });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/drinks/DrinkCard.jsx", error: String((e && e.message) || e) }); }

// components/feedback/Badge.jsx
try { (() => {
function Badge({
  children,
  variant = 'warning',
  style: styleProp,
  ...props
}) {
  const variants = {
    warning: {
      background: 'var(--warning-bg)',
      color: 'var(--warning-text)'
    },
    success: {
      background: 'var(--success-bg)',
      color: 'var(--success)'
    },
    neutral: {
      background: 'var(--beige-100)',
      color: 'var(--text-secondary)'
    }
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
    ...styleProp
  };
  return React.createElement('span', {
    style: badgeStyle,
    ...props
  }, children);
}
Object.assign(__ds_scope, { Badge });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/feedback/Badge.jsx", error: String((e && e.message) || e) }); }

// components/inputs/InputField.jsx
try { (() => {
function InputField({
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
    ...styleProp
  };
  const labelStyle = {
    fontSize: 'var(--font-size-label)',
    fontWeight: 'var(--font-weight-medium)',
    color: 'var(--text-primary)'
  };
  const inputStyle = {
    fontFamily: 'var(--font-family)',
    fontSize: 'var(--font-size-body)',
    fontWeight: 'var(--font-weight-regular)',
    color: 'var(--text-primary)',
    background: 'var(--surface-input)',
    border: focused ? '2px solid var(--brown-600)' : error ? '2px solid var(--warning)' : '1px solid var(--border-medium)',
    borderRadius: 'var(--radius-md)',
    padding: '10px 14px',
    outline: 'none',
    opacity: disabled ? 0.5 : 1,
    width: '100%',
    boxSizing: 'border-box'
  };
  const errorStyle = {
    fontSize: 'var(--font-size-caption)',
    fontWeight: 'var(--font-weight-medium)',
    color: 'var(--warning)',
    margin: 0
  };
  return React.createElement('div', {
    style: wrapStyle
  }, label && React.createElement('label', {
    style: labelStyle
  }, label), React.createElement('input', {
    type,
    placeholder,
    value,
    onChange,
    disabled,
    style: inputStyle,
    onFocus: () => setFocused(true),
    onBlur: () => setFocused(false),
    ...props
  }), error && React.createElement('p', {
    style: errorStyle
  }, error));
}
Object.assign(__ds_scope, { InputField });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/inputs/InputField.jsx", error: String((e && e.message) || e) }); }

// components/navigation/SidebarNav.jsx
try { (() => {
function SidebarNav({
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
    ...styleProp
  };
  const brandStyle = {
    fontSize: 'var(--font-size-heading)',
    fontWeight: 'var(--font-weight-bold)',
    color: 'var(--text-on-dark)',
    margin: 0,
    padding: '0 var(--space-24)',
    marginBottom: 'var(--space-32)',
    lineHeight: 'var(--line-height-tight)'
  };
  const navStyle = {
    display: 'flex',
    flexDirection: 'column',
    gap: 'var(--space-4)',
    padding: '0 var(--space-12)',
    flex: 1
  };
  const getItemStyle = item => {
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
      background: isActive ? 'var(--brown-600)' : isHover && isLogout ? '#7A2E1A' : isHover ? '#5C3A27' : 'transparent',
      color: isActive ? 'var(--white)' : isHover ? 'var(--text-on-dark)' : 'var(--beige-300)',
      cursor: 'pointer',
      fontSize: 'var(--font-size-label)',
      fontWeight: isActive ? 'var(--font-weight-semibold)' : 'var(--font-weight-medium)',
      fontFamily: 'var(--font-family)',
      border: 'none',
      textAlign: 'left',
      width: '100%',
      boxSizing: 'border-box'
    };
  };
  return React.createElement('nav', {
    style: sidebarStyle,
    ...props
  }, React.createElement('h1', {
    style: brandStyle
  }, brandName), React.createElement('div', {
    style: navStyle
  }, items.filter(i => i.id !== 'logout').map(item => React.createElement('button', {
    key: item.id,
    style: getItemStyle(item),
    onClick: () => onSelect && onSelect(item.id),
    onMouseEnter: () => setHoveredId(item.id),
    onMouseLeave: () => setHoveredId(null)
  }, item.icon && React.createElement('span', {
    style: {
      fontSize: '18px',
      width: '20px',
      textAlign: 'center',
      flexShrink: 0
    }
  }, item.icon), item.label))), items.find(i => i.id === 'logout') && React.createElement('div', {
    style: {
      padding: '0 var(--space-12)',
      marginTop: 'auto'
    }
  }, React.createElement('button', {
    style: getItemStyle(items.find(i => i.id === 'logout')),
    onClick: () => onSelect && onSelect('logout'),
    onMouseEnter: () => setHoveredId('logout'),
    onMouseLeave: () => setHoveredId(null)
  }, items.find(i => i.id === 'logout').icon && React.createElement('span', {
    style: {
      fontSize: '18px',
      width: '20px',
      textAlign: 'center',
      flexShrink: 0
    }
  }, items.find(i => i.id === 'logout').icon), items.find(i => i.id === 'logout').label)));
}
Object.assign(__ds_scope, { SidebarNav });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/navigation/SidebarNav.jsx", error: String((e && e.message) || e) }); }

// ui_kits/desktop_app/DashboardScreen.jsx
try { (() => {
const dashStyles = {
  page: {
    padding: '32px',
    display: 'flex',
    flexDirection: 'column',
    gap: '24px',
    flex: 1,
    overflow: 'auto',
    background: 'var(--surface-page)',
    fontFamily: 'var(--font-family)'
  },
  header: {
    fontSize: '28px',
    fontWeight: 700,
    color: 'var(--text-primary)',
    margin: 0
  },
  cards: {
    display: 'flex',
    gap: '16px',
    flexWrap: 'wrap'
  },
  section: {
    display: 'flex',
    gap: '24px'
  },
  chartBox: {
    flex: 2,
    background: 'var(--white)',
    border: '1px solid var(--border-light)',
    borderRadius: '12px',
    padding: '24px',
    display: 'flex',
    flexDirection: 'column',
    gap: '12px'
  },
  chartTitle: {
    fontSize: '16px',
    fontWeight: 600,
    color: 'var(--text-primary)',
    margin: 0
  },
  chartArea: {
    display: 'flex',
    gap: '8px',
    alignItems: 'flex-end',
    height: '160px',
    paddingTop: '8px'
  },
  bar: {
    flex: 1,
    height: '100%',
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'flex-end',
    alignItems: 'center'
  },
  barLabel: {
    fontSize: '11px',
    color: 'var(--text-secondary)',
    textAlign: 'center',
    marginTop: '6px'
  },
  barVal: {
    fontSize: '11px',
    fontWeight: 600,
    color: 'var(--white)'
  },
  listBox: {
    flex: 1,
    background: 'var(--white)',
    border: '1px solid var(--border-light)',
    borderRadius: '12px',
    padding: '24px',
    display: 'flex',
    flexDirection: 'column',
    gap: '12px'
  }
};
function DashboardScreen() {
  const {
    StatCard,
    DataTable
  } = window.CafLumiReDesignSystem_77c5a3;
  const drinks = [{
    name: 'Americano',
    val: 84,
    color: 'var(--chart-1)'
  }, {
    name: 'Latte',
    val: 72,
    color: 'var(--chart-2)'
  }, {
    name: 'Cappuccino',
    val: 65,
    color: 'var(--chart-3)'
  }, {
    name: 'Espresso',
    val: 45,
    color: 'var(--chart-4)'
  }, {
    name: 'Mocha',
    val: 38,
    color: 'var(--chart-5)'
  }];
  const maxVal = Math.max(...drinks.map(d => d.val));
  const orders = [{
    id: '#1042',
    customer: 'Maria Santos',
    items: '2',
    total: '$12.50',
    time: '10:42 AM'
  }, {
    id: '#1041',
    customer: 'John Cruz',
    items: '1',
    total: '$5.00',
    time: '10:38 AM'
  }, {
    id: '#1040',
    customer: 'Ana Reyes',
    items: '3',
    total: '$18.00',
    time: '10:25 AM'
  }, {
    id: '#1039',
    customer: 'Carlos Tan',
    items: '1',
    total: '$4.50',
    time: '10:12 AM'
  }];
  return React.createElement('div', {
    style: dashStyles.page
  }, React.createElement('h1', {
    style: dashStyles.header
  }, 'Dashboard'), React.createElement('div', {
    style: dashStyles.cards
  }, React.createElement(StatCard, {
    label: 'Total Revenue',
    value: '$12,450',
    trend: 'up',
    trendLabel: '+12%'
  }), React.createElement(StatCard, {
    label: 'Orders',
    value: '342',
    trend: 'up',
    trendLabel: '+8%'
  }), React.createElement(StatCard, {
    label: 'Customers',
    value: '186',
    trend: 'neutral',
    trendLabel: '—'
  }), React.createElement(StatCard, {
    label: 'Avg Order Value',
    value: '$8.50',
    trend: 'down',
    trendLabel: '-2%'
  })), React.createElement('div', {
    style: dashStyles.section
  }, React.createElement('div', {
    style: dashStyles.chartBox
  }, React.createElement('p', {
    style: dashStyles.chartTitle
  }, 'Most Popular Drinks'), React.createElement('div', {
    style: dashStyles.chartArea
  }, drinks.map((d, i) => React.createElement('div', {
    key: i,
    style: {
      ...dashStyles.bar
    }
  }, React.createElement('div', {
    style: {
      width: '100%',
      height: d.val / maxVal * 100 + '%',
      background: d.color,
      borderRadius: '4px 4px 0 0',
      display: 'flex',
      alignItems: 'flex-end',
      justifyContent: 'center',
      paddingBottom: '4px',
      minHeight: '20px'
    }
  }, React.createElement('span', {
    style: dashStyles.barVal
  }, d.val)), React.createElement('span', {
    style: dashStyles.barLabel
  }, d.name))))), React.createElement('div', {
    style: dashStyles.listBox
  }, React.createElement('p', {
    style: dashStyles.chartTitle
  }, 'Recent Orders'), React.createElement(DataTable, {
    columns: [{
      key: 'id',
      label: 'ID',
      width: '70px'
    }, {
      key: 'customer',
      label: 'Customer'
    }, {
      key: 'total',
      label: 'Total',
      align: 'right',
      width: '70px'
    }, {
      key: 'time',
      label: 'Time',
      width: '80px'
    }],
    rows: orders
  }))));
}
window.DashboardScreen = DashboardScreen;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/desktop_app/DashboardScreen.jsx", error: String((e && e.message) || e) }); }

// ui_kits/desktop_app/InventoryScreen.jsx
try { (() => {
const invStyles = {
  page: {
    padding: '32px',
    display: 'flex',
    flexDirection: 'column',
    gap: '24px',
    flex: 1,
    overflow: 'auto',
    background: 'var(--surface-page)',
    fontFamily: 'var(--font-family)'
  },
  header: {
    fontSize: '28px',
    fontWeight: 700,
    color: 'var(--text-primary)',
    margin: 0
  }
};
function InventoryScreen() {
  const {
    DataTable,
    Badge,
    Button
  } = window.CafLumiReDesignSystem_77c5a3;
  const rows = [{
    ingredient: 'Coffee Beans (Arabica)',
    stock: '2.4',
    unit: 'kg',
    threshold: 3,
    status: 'low'
  }, {
    ingredient: 'Whole Milk',
    stock: '8.5',
    unit: 'L',
    threshold: 5,
    status: 'ok'
  }, {
    ingredient: 'Oat Milk',
    stock: '1.2',
    unit: 'L',
    threshold: 3,
    status: 'low'
  }, {
    ingredient: 'Sugar',
    stock: '4.0',
    unit: 'kg',
    threshold: 2,
    status: 'ok'
  }, {
    ingredient: 'Vanilla Syrup',
    stock: '0.8',
    unit: 'L',
    threshold: 1,
    status: 'low'
  }, {
    ingredient: 'Chocolate Powder',
    stock: '1.5',
    unit: 'kg',
    threshold: 1,
    status: 'ok'
  }, {
    ingredient: 'Cups (12 oz)',
    stock: '142',
    unit: 'pcs',
    threshold: 50,
    status: 'ok'
  }, {
    ingredient: 'Cups (16 oz)',
    stock: '38',
    unit: 'pcs',
    threshold: 50,
    status: 'low'
  }];
  return React.createElement('div', {
    style: invStyles.page
  }, React.createElement('h1', {
    style: invStyles.header
  }, 'Inventory'), React.createElement(DataTable, {
    columns: [{
      key: 'ingredient',
      label: 'Ingredient'
    }, {
      key: 'stock',
      label: 'Stock',
      align: 'right',
      width: '80px'
    }, {
      key: 'unit',
      label: 'Unit',
      width: '60px'
    }, {
      key: 'status',
      label: 'Status',
      width: '110px'
    }, {
      key: 'action',
      label: '',
      width: '120px'
    }],
    rows,
    renderCell: (row, col) => {
      if (col.key === 'status') return React.createElement(Badge, {
        variant: row.status === 'low' ? 'warning' : 'success'
      }, row.status === 'low' ? 'Low Stock' : 'In Stock');
      if (col.key === 'action' && row.status === 'low') return React.createElement(Button, {
        variant: 'danger',
        size: 'sm'
      }, 'Restock');
      return row[col.key];
    }
  }));
}
window.InventoryScreen = InventoryScreen;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/desktop_app/InventoryScreen.jsx", error: String((e && e.message) || e) }); }

// ui_kits/desktop_app/LoginScreen.jsx
try { (() => {
const loginScreenStyles = {
  wrapper: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    width: '100%',
    height: '100%',
    background: 'linear-gradient(to bottom, #4E2E1E, #5C3A27)',
    fontFamily: 'var(--font-family)'
  },
  card: {
    background: 'var(--white)',
    borderRadius: 'var(--radius-xl)',
    padding: '48px 40px',
    width: '360px',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    gap: '24px'
  },
  brand: {
    fontSize: '32px',
    fontWeight: 700,
    color: 'var(--brown-900)',
    margin: 0,
    lineHeight: 1.2
  },
  sub: {
    fontSize: '14px',
    color: 'var(--text-secondary)',
    margin: '0',
    letterSpacing: '0.1em',
    textTransform: 'uppercase',
    fontWeight: 500
  },
  form: {
    width: '100%',
    display: 'flex',
    flexDirection: 'column',
    gap: '16px'
  }
};
function LoginScreen({
  onLogin
}) {
  const {
    InputField,
    Button
  } = window.CafLumiReDesignSystem_77c5a3;
  const [user, setUser] = React.useState('');
  const [pass, setPass] = React.useState('');
  return React.createElement('div', {
    style: loginScreenStyles.wrapper
  }, React.createElement('div', {
    style: loginScreenStyles.card
  }, React.createElement('h1', {
    style: loginScreenStyles.brand
  }, 'Café Lumière'), React.createElement('p', {
    style: loginScreenStyles.sub
  }, 'Coffee Management'), React.createElement('div', {
    style: loginScreenStyles.form
  }, React.createElement(InputField, {
    label: 'Username',
    placeholder: 'Enter username',
    value: user,
    onChange: e => setUser(e.target.value)
  }), React.createElement(InputField, {
    label: 'Password',
    type: 'password',
    placeholder: '••••••••',
    value: pass,
    onChange: e => setPass(e.target.value)
  }), React.createElement(Button, {
    variant: 'primary',
    fullWidth: true,
    size: 'lg',
    onClick: onLogin
  }, 'Sign In'))));
}
window.LoginScreen = LoginScreen;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/desktop_app/LoginScreen.jsx", error: String((e && e.message) || e) }); }

// ui_kits/desktop_app/OrderEntryScreen.jsx
try { (() => {
const orderStyles = {
  page: {
    padding: '32px',
    display: 'flex',
    flexDirection: 'column',
    gap: '24px',
    flex: 1,
    overflow: 'auto',
    background: 'var(--surface-page)',
    fontFamily: 'var(--font-family)'
  },
  header: {
    fontSize: '28px',
    fontWeight: 700,
    color: 'var(--text-primary)',
    margin: 0
  },
  row: {
    display: 'flex',
    gap: '16px',
    alignItems: 'flex-start'
  },
  selectWrap: {
    display: 'flex',
    flexDirection: 'column',
    gap: '4px'
  },
  selectLabel: {
    fontSize: '14px',
    fontWeight: 500,
    color: 'var(--text-primary)'
  },
  select: {
    fontFamily: 'var(--font-family)',
    fontSize: '16px',
    padding: '10px 14px',
    border: '1px solid var(--border-medium)',
    borderRadius: '8px',
    background: 'var(--white)',
    color: 'var(--text-primary)',
    minWidth: '220px'
  },
  drinks: {
    display: 'flex',
    gap: '16px',
    flexWrap: 'wrap'
  }
};
function OrderEntryScreen() {
  const {
    DrinkCard,
    Button
  } = window.CafLumiReDesignSystem_77c5a3;
  const drinks = [{
    name: 'Americano',
    price: '$4.50',
    ph: 'A'
  }, {
    name: 'Latte',
    price: '$5.00',
    ph: 'L'
  }, {
    name: 'Cappuccino',
    price: '$5.50',
    ph: 'C'
  }, {
    name: 'Espresso',
    price: '$3.50',
    ph: 'E'
  }, {
    name: 'Mocha',
    price: '$6.00',
    ph: 'M'
  }];
  return React.createElement('div', {
    style: orderStyles.page
  }, React.createElement('h1', {
    style: orderStyles.header
  }, 'Order Entry'), React.createElement('div', {
    style: orderStyles.row
  }, React.createElement('div', {
    style: orderStyles.selectWrap
  }, React.createElement('span', {
    style: orderStyles.selectLabel
  }, 'Customer'), React.createElement('select', {
    style: orderStyles.select,
    defaultValue: 'maria'
  }, React.createElement('option', {
    value: 'maria'
  }, 'Maria Santos'), React.createElement('option', {
    value: 'john'
  }, 'John Cruz'), React.createElement('option', {
    value: 'ana'
  }, 'Ana Reyes'), React.createElement('option', {
    value: 'carlos'
  }, 'Carlos Tan')))), React.createElement('div', {
    style: orderStyles.drinks
  }, drinks.map((d, i) => React.createElement(DrinkCard, {
    key: i,
    name: d.name,
    price: d.price,
    imagePlaceholder: d.ph
  }))));
}
window.OrderEntryScreen = OrderEntryScreen;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/desktop_app/OrderEntryScreen.jsx", error: String((e && e.message) || e) }); }

// ui_kits/desktop_app/RevenueScreen.jsx
try { (() => {
const revStyles = {
  page: {
    padding: '32px',
    display: 'flex',
    flexDirection: 'column',
    gap: '24px',
    flex: 1,
    overflow: 'auto',
    background: 'var(--surface-page)',
    fontFamily: 'var(--font-family)'
  },
  header: {
    fontSize: '28px',
    fontWeight: 700,
    color: 'var(--text-primary)',
    margin: 0
  },
  dateRow: {
    display: 'flex',
    gap: '12px',
    alignItems: 'center'
  },
  dateLabel: {
    fontSize: '14px',
    fontWeight: 500,
    color: 'var(--text-primary)'
  },
  dateInput: {
    fontFamily: 'var(--font-family)',
    fontSize: '16px',
    padding: '10px 14px',
    border: '1px solid var(--border-medium)',
    borderRadius: '8px',
    background: 'var(--white)',
    color: 'var(--text-primary)'
  },
  cards: {
    display: 'flex',
    gap: '16px',
    flexWrap: 'wrap'
  }
};
function RevenueScreen() {
  const {
    StatCard,
    DataTable
  } = window.CafLumiReDesignSystem_77c5a3;
  const orders = [{
    id: '#1042',
    customer: 'Maria Santos',
    items: 'Americano x2',
    total: '$9.00'
  }, {
    id: '#1041',
    customer: 'John Cruz',
    items: 'Latte x1',
    total: '$5.00'
  }, {
    id: '#1040',
    customer: 'Ana Reyes',
    items: 'Mocha x1, Espresso x2',
    total: '$13.00'
  }, {
    id: '#1039',
    customer: 'Carlos Tan',
    items: 'Cappuccino x1',
    total: '$5.50'
  }];
  return React.createElement('div', {
    style: revStyles.page
  }, React.createElement('h1', {
    style: revStyles.header
  }, 'Revenue Summary'), React.createElement('div', {
    style: revStyles.dateRow
  }, React.createElement('span', {
    style: revStyles.dateLabel
  }, 'Date'), React.createElement('input', {
    type: 'date',
    style: revStyles.dateInput,
    defaultValue: '2026-06-23'
  })), React.createElement('div', {
    style: revStyles.cards
  }, React.createElement(StatCard, {
    label: 'Revenue Today',
    value: '$485.50',
    trend: 'up',
    trendLabel: '+18% vs yesterday'
  }), React.createElement(StatCard, {
    label: 'Orders Today',
    value: '47',
    trend: 'up',
    trendLabel: '+5'
  }), React.createElement(StatCard, {
    label: 'Customers Today',
    value: '32'
  }), React.createElement(StatCard, {
    label: 'Avg Order Value',
    value: '$10.33',
    trend: 'up',
    trendLabel: '+$1.83'
  })), React.createElement(DataTable, {
    columns: [{
      key: 'id',
      label: 'Order ID',
      width: '80px'
    }, {
      key: 'customer',
      label: 'Customer'
    }, {
      key: 'items',
      label: 'Items'
    }, {
      key: 'total',
      label: 'Total',
      align: 'right',
      width: '90px'
    }],
    rows: orders
  }));
}
window.RevenueScreen = RevenueScreen;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/desktop_app/RevenueScreen.jsx", error: String((e && e.message) || e) }); }

__ds_ns.StatCard = __ds_scope.StatCard;

__ds_ns.Button = __ds_scope.Button;

__ds_ns.DataTable = __ds_scope.DataTable;

__ds_ns.DrinkCard = __ds_scope.DrinkCard;

__ds_ns.Badge = __ds_scope.Badge;

__ds_ns.InputField = __ds_scope.InputField;

__ds_ns.SidebarNav = __ds_scope.SidebarNav;

})();
