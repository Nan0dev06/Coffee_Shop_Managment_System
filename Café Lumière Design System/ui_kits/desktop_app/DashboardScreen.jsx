const dashStyles = {
  page: { padding:'32px', display:'flex', flexDirection:'column', gap:'24px', flex:1, overflow:'auto', background:'var(--surface-page)', fontFamily:'var(--font-family)' },
  header: { fontSize:'28px', fontWeight:700, color:'var(--text-primary)', margin:0 },
  cards: { display:'flex', gap:'16px', flexWrap:'wrap' },
  section: { display:'flex', gap:'24px' },
  chartBox: { flex:2, background:'var(--white)', border:'1px solid var(--border-light)', borderRadius:'12px', padding:'24px', display:'flex', flexDirection:'column', gap:'12px' },
  chartTitle: { fontSize:'16px', fontWeight:600, color:'var(--text-primary)', margin:0 },
  chartArea: { display:'flex', gap:'8px', alignItems:'flex-end', height:'160px', paddingTop:'8px' },
  bar: { flex:1, height:'100%', display:'flex', flexDirection:'column', justifyContent:'flex-end', alignItems:'center' },
  barLabel: { fontSize:'11px', color:'var(--text-secondary)', textAlign:'center', marginTop:'6px' },
  barVal: { fontSize:'11px', fontWeight:600, color:'var(--white)' },
  listBox: { flex:1, background:'var(--white)', border:'1px solid var(--border-light)', borderRadius:'12px', padding:'24px', display:'flex', flexDirection:'column', gap:'12px' },
};

function DashboardScreen() {
  const { StatCard, DataTable } = window.CafLumiReDesignSystem_77c5a3;
  const drinks = [
    { name:'Americano', val:84, color:'var(--chart-1)' },
    { name:'Latte', val:72, color:'var(--chart-2)' },
    { name:'Cappuccino', val:65, color:'var(--chart-3)' },
    { name:'Espresso', val:45, color:'var(--chart-4)' },
    { name:'Mocha', val:38, color:'var(--chart-5)' },
  ];
  const maxVal = Math.max(...drinks.map(d=>d.val));
  const orders = [
    { id:'#1042', customer:'Maria Santos', items:'2', total:'$12.50', time:'10:42 AM' },
    { id:'#1041', customer:'John Cruz', items:'1', total:'$5.00', time:'10:38 AM' },
    { id:'#1040', customer:'Ana Reyes', items:'3', total:'$18.00', time:'10:25 AM' },
    { id:'#1039', customer:'Carlos Tan', items:'1', total:'$4.50', time:'10:12 AM' },
  ];
  return React.createElement('div', { style: dashStyles.page },
    React.createElement('h1', { style: dashStyles.header }, 'Dashboard'),
    React.createElement('div', { style: dashStyles.cards },
      React.createElement(StatCard, { label:'Total Revenue', value:'$12,450', trend:'up', trendLabel:'+12%' }),
      React.createElement(StatCard, { label:'Orders', value:'342', trend:'up', trendLabel:'+8%' }),
      React.createElement(StatCard, { label:'Customers', value:'186', trend:'neutral', trendLabel:'—' }),
      React.createElement(StatCard, { label:'Avg Order Value', value:'$8.50', trend:'down', trendLabel:'-2%' }),
    ),
    React.createElement('div', { style: dashStyles.section },
      React.createElement('div', { style: dashStyles.chartBox },
        React.createElement('p', { style: dashStyles.chartTitle }, 'Most Popular Drinks'),
        React.createElement('div', { style: dashStyles.chartArea },
          drinks.map((d, i) => React.createElement('div', { key: i, style: { ...dashStyles.bar } },
            React.createElement('div', { style: { width:'100%', height: (d.val/maxVal*100)+'%', background:d.color, borderRadius:'4px 4px 0 0', display:'flex', alignItems:'flex-end', justifyContent:'center', paddingBottom:'4px', minHeight:'20px' } },
              React.createElement('span', { style: dashStyles.barVal }, d.val)
            ),
            React.createElement('span', { style: dashStyles.barLabel }, d.name),
          ))
        )
      ),
      React.createElement('div', { style: dashStyles.listBox },
        React.createElement('p', { style: dashStyles.chartTitle }, 'Recent Orders'),
        React.createElement(DataTable, {
          columns: [
            { key:'id', label:'ID', width:'70px' },
            { key:'customer', label:'Customer' },
            { key:'total', label:'Total', align:'right', width:'70px' },
            { key:'time', label:'Time', width:'80px' },
          ],
          rows: orders,
        })
      )
    )
  );
}
window.DashboardScreen = DashboardScreen;
