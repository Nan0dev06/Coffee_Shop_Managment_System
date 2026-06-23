const orderStyles = {
  page: { padding:'32px', display:'flex', flexDirection:'column', gap:'24px', flex:1, overflow:'auto', background:'var(--surface-page)', fontFamily:'var(--font-family)' },
  header: { fontSize:'28px', fontWeight:700, color:'var(--text-primary)', margin:0 },
  row: { display:'flex', gap:'16px', alignItems:'flex-start' },
  selectWrap: { display:'flex', flexDirection:'column', gap:'4px' },
  selectLabel: { fontSize:'14px', fontWeight:500, color:'var(--text-primary)' },
  select: { fontFamily:'var(--font-family)', fontSize:'16px', padding:'10px 14px', border:'1px solid var(--border-medium)', borderRadius:'8px', background:'var(--white)', color:'var(--text-primary)', minWidth:'220px' },
  drinks: { display:'flex', gap:'16px', flexWrap:'wrap' },
};

function OrderEntryScreen() {
  const { DrinkCard, Button } = window.CafLumiReDesignSystem_77c5a3;
  const drinks = [
    { name:'Americano', price:'$4.50', ph:'A' },
    { name:'Latte', price:'$5.00', ph:'L' },
    { name:'Cappuccino', price:'$5.50', ph:'C' },
    { name:'Espresso', price:'$3.50', ph:'E' },
    { name:'Mocha', price:'$6.00', ph:'M' },
  ];
  return React.createElement('div', { style: orderStyles.page },
    React.createElement('h1', { style: orderStyles.header }, 'Order Entry'),
    React.createElement('div', { style: orderStyles.row },
      React.createElement('div', { style: orderStyles.selectWrap },
        React.createElement('span', { style: orderStyles.selectLabel }, 'Customer'),
        React.createElement('select', { style: orderStyles.select, defaultValue:'maria' },
          React.createElement('option', { value:'maria' }, 'Maria Santos'),
          React.createElement('option', { value:'john' }, 'John Cruz'),
          React.createElement('option', { value:'ana' }, 'Ana Reyes'),
          React.createElement('option', { value:'carlos' }, 'Carlos Tan'),
        )
      )
    ),
    React.createElement('div', { style: orderStyles.drinks },
      drinks.map((d, i) => React.createElement(DrinkCard, { key:i, name:d.name, price:d.price, imagePlaceholder:d.ph }))
    )
  );
}
window.OrderEntryScreen = OrderEntryScreen;
