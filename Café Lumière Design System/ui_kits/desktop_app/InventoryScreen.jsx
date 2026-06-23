const invStyles = {
  page: { padding:'32px', display:'flex', flexDirection:'column', gap:'24px', flex:1, overflow:'auto', background:'var(--surface-page)', fontFamily:'var(--font-family)' },
  header: { fontSize:'28px', fontWeight:700, color:'var(--text-primary)', margin:0 },
};

function InventoryScreen() {
  const { DataTable, Badge, Button } = window.CafLumiReDesignSystem_77c5a3;
  const rows = [
    { ingredient:'Coffee Beans (Arabica)', stock:'2.4', unit:'kg', threshold:3, status:'low' },
    { ingredient:'Whole Milk', stock:'8.5', unit:'L', threshold:5, status:'ok' },
    { ingredient:'Oat Milk', stock:'1.2', unit:'L', threshold:3, status:'low' },
    { ingredient:'Sugar', stock:'4.0', unit:'kg', threshold:2, status:'ok' },
    { ingredient:'Vanilla Syrup', stock:'0.8', unit:'L', threshold:1, status:'low' },
    { ingredient:'Chocolate Powder', stock:'1.5', unit:'kg', threshold:1, status:'ok' },
    { ingredient:'Cups (12 oz)', stock:'142', unit:'pcs', threshold:50, status:'ok' },
    { ingredient:'Cups (16 oz)', stock:'38', unit:'pcs', threshold:50, status:'low' },
  ];
  return React.createElement('div', { style: invStyles.page },
    React.createElement('h1', { style: invStyles.header }, 'Inventory'),
    React.createElement(DataTable, {
      columns: [
        { key:'ingredient', label:'Ingredient' },
        { key:'stock', label:'Stock', align:'right', width:'80px' },
        { key:'unit', label:'Unit', width:'60px' },
        { key:'status', label:'Status', width:'110px' },
        { key:'action', label:'', width:'120px' },
      ],
      rows,
      renderCell: (row, col) => {
        if (col.key === 'status') return React.createElement(Badge, { variant: row.status === 'low' ? 'warning' : 'success' }, row.status === 'low' ? 'Low Stock' : 'In Stock');
        if (col.key === 'action' && row.status === 'low') return React.createElement(Button, { variant:'danger', size:'sm' }, 'Restock');
        return row[col.key];
      }
    })
  );
}
window.InventoryScreen = InventoryScreen;
