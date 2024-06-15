const formLoginEl = document.getElementById('loginForm');
const emailEl = document.getElementById('email');
const passwordEl = document.getElementById('password');
formLoginEl.addEventListener('submit', async function (e) {
    e.preventDefault();
    const email = emailEl.value;
    const password = passwordEl.value;
    const data = {
        email,
        password
    };
    try {
        const response = await axios.post('/api/login', data);
        if (response.status === 200) {
            toastr.success('Đăng nhập thành công');
            setTimeout(() => {
                window.location.href = '/';
            }, 1000);
        }
    } catch (error) {
        toastr.error('Đăng nhập thất bại');
    }
});