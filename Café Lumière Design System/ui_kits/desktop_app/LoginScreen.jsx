const loginScreenStyles = {
  wrapper: { display:'flex', alignItems:'center', justifyContent:'center', width:'100%', height:'100%', background:'linear-gradient(to bottom, #4E2E1E, #5C3A27)', fontFamily:'var(--font-family)' },
  card: { background:'var(--white)', borderRadius:'var(--radius-xl)', padding:'48px 40px', width:'360px', display:'flex', flexDirection:'column', alignItems:'center', gap:'24px' },
  brand: { fontSize:'32px', fontWeight:700, color:'var(--brown-900)', margin:0, lineHeight:1.2 },
  sub: { fontSize:'14px', color:'var(--text-secondary)', margin:'0', letterSpacing:'0.1em', textTransform:'uppercase', fontWeight:500 },
  form: { width:'100%', display:'flex', flexDirection:'column', gap:'16px' },
};

function LoginScreen({ onLogin }) {
  const { InputField, Button } = window.CafLumiReDesignSystem_77c5a3;
  const [user, setUser] = React.useState('');
  const [pass, setPass] = React.useState('');
  return React.createElement('div', { style: loginScreenStyles.wrapper },
    React.createElement('div', { style: loginScreenStyles.card },
      React.createElement('h1', { style: loginScreenStyles.brand }, 'Café Lumière'),
      React.createElement('p', { style: loginScreenStyles.sub }, 'Coffee Management'),
      React.createElement('div', { style: loginScreenStyles.form },
        React.createElement(InputField, { label:'Username', placeholder:'Enter username', value:user, onChange:e=>setUser(e.target.value) }),
        React.createElement(InputField, { label:'Password', type:'password', placeholder:'••••••••', value:pass, onChange:e=>setPass(e.target.value) }),
        React.createElement(Button, { variant:'primary', fullWidth:true, size:'lg', onClick:onLogin }, 'Sign In')
      )
    )
  );
}
window.LoginScreen = LoginScreen;
