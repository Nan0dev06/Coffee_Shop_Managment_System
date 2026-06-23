const revStyles = {
  page: { padding:'32px', display:'flex', flexDirection:'column', gap:'24px', flex:1, overflow:'auto', background:'var(--surface-page)', fontFamily:'var(--font-family)' },
  header: { fontSize:'28px', fontWeight:700, color:'var(--text-primary)', margin:0 },
  dateRow: { display:'flex', gap:'12px', alignItems:'center' },
  dateLabel: { fontSize:'14px', fontWeight:500, color:'var(--text-primary)' },
  dateInput: { fontFamily:'var(--font-family)', fontSize:'16px', padding:'10px 14px', border:'1px solid var(--border-medium)', borderRadius:'8px', background:'var(--white)', color:'var(--text-primary)' },
  cards: { display:'flex', gap:'16px', flexWrap:'wrap' },
};

function RevenueScreen() {
  const { StatCard, DataTable } = window.CafLumiReDesignSystem_77c5a3;
  const orders = [
    { id:'#1042', customer:'Maria Santos', items:'Americano x2', total:'$9.00' },
    { id:'#1041', customer:'John Cruz', items:'Latte x1', total:'$5.00' },
    { id:'#1040', customer:'Ana Reyes', items:'Mocha x1, Espresso x2', total:'$13.00' },
    { id:'#1039', customer:'Carlos Tan', items:'Cappuccino x1', total:'$5.50' },
  ];
  return React.createElement('div', { style: revStyles.page },
    React.createElement('h1', { style: revStyles.header }, 'Revenue Summary'),
    React.createElement('div', { style: revStyles.dateRow },
      React.createElement('span', { style: revStyles.dateLabel }, 'Date'),
      React.createElement('input', { type:'date', style: revStyles.dateInput, defaultValue:'2026-06-23' }),
    ),
    React.createElement('div', { style: revStyles.cards },
      React.createElement(StatCard, { label:'Revenue Today', value:'$485.50', trend:'up', trendLabel:'+18% vs yesterday' }),
      React.createElement(StatCard, { label:'Orders Today', value:'47', trend:'up', trendLabel:'+5' }),
      React.createElement(StatCard, { label:'Customers Today', value:'32' }),
      React.createElement(StatCard, { label:'Avg Order Value', value:'$10.33', trend:'up', trendLabel:'+$1.83' }),
    ),
    React.createElement(DataTable, {
      columns: [
        { key:'id', label:'Order ID', width:'80px' },
        { key:'customer', label:'Customer' },
        { key:'items', label:'Items' },
        { key:'total', label:'Total', align:'right', width:'90px' },
      ],
      rows: orders,
    })
  );
}
window.RevenueScreen = RevenueScreen;
