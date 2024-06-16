function updateProfile() {
    const name = document.getElementById('name').value;

    if (!name) {
        document.getElementById('name').classList.add('is-invalid');
        return;
    } else {
        document.getElementById('name').classList.remove('is-invalid');
    }

    const Infodata = {
        name: name
    };

    axios.put('/api/users/update-profile', Infodata)
        .then(response => {
            toastr.success('Cập nhật thông tin cá nhân thành công!');
        })
        .catch(error => {
            console.error('Error:', error);
            toastr.error('Đã có lỗi xảy ra trong quá trình cập nhật thông tin cá nhân: ' + error.response.data);
        });
}
function updatePassword() {
    const oldPassword = document.getElementById('oldPassword').value;
    const newPassword = document.getElementById('newPassword').value;
    const confirmPassword = document.getElementById('confirmPassword').value;

    if (!oldPassword) {
        document.getElementById('oldPassword').classList.add('is-invalid');
        return;
    } else {
        document.getElementById('oldPassword').classList.remove('is-invalid');
    }

    if (!newPassword) {
        document.getElementById('newPassword').classList.add('is-invalid');
        return;
    } else {
        document.getElementById('newPassword').classList.remove('is-invalid');
    }

    if (!confirmPassword) {
        document.getElementById('confirmPassword').classList.add('is-invalid');
        return;
    } else {
        document.getElementById('confirmPassword').classList.remove('is-invalid');
    }

    if (newPassword !== confirmPassword) {
        toastr.error('Mật khẩu xác nhận không trùng khớp');
        return;
    }

    const data = {
        oldPassword: oldPassword,
        newPassword: newPassword,
        confirmPassword: confirmPassword
    };

    axios.put('/api/users/update-password', data)
        .then(response => {
            toastr.success('Cập nhật mật khẩu thành công!');
            document.getElementById('oldPassword').value = '';
            document.getElementById('newPassword').value = '';
            document.getElementById('confirmPassword').value = '';
        })
        .catch(error => {
            console.error('Error:', error);
            toastr.error('Đã có lỗi xảy ra trong quá trình thay đổi mật khẩu: ' + error.response.data);
        });
}