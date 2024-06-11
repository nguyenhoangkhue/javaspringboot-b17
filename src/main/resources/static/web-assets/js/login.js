document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const loginData={
        email: email,
        password: password
    }

    axios.post('/api/login', loginData)
        .then(function (response) {
            if (response.data.success) {
                toastr.success('Thành công', 'Đăng nhập thành công!');
            } else {
                toastr.error('Lỗi', 'Sai email hoặc mật khẩu!');
            }
        })
        .catch(error => {
            console.error(error);
            toastr.error('Lỗi', 'Đã xảy ra lỗi khi đăng nhập!');
        });
});