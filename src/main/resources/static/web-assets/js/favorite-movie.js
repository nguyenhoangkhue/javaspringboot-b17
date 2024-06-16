const btnFavorite=document.getElementById('btn-favorite');

btnFavorite.addEventListener('click',function (){
    if(isFavorite){
        deleteFromFavorite(movie.id)
    }else {
        addToFavorite(movie.id)
    }
})
const addToFavorite=(movieId)=>{
    axios.post(`/api/favorites?movieId=${movieId}`)
        .then(response=>{
            toastr.success('Thêm thành công')
            isFavorite=true;
            btnFavorite.innerHTML=`Xóa khỏi yêu thích`
        })
        .catch(error=>{
            console.log(error)
            toastr.error('Đã có lỗi xảy ra')
        })
}
const deleteFromFavorite=(movieId)=>{
    axios.delete(`/api/favorites?movieId=${movieId}`)
        .then(response=>{
            toastr.success('Xóa thành công')
            isFavorite=false;
            btnFavorite.innerHTML=`Thêm vào yêu thích`
        })
        .catch(error=>{
            console.log(error)
            toastr.error('Đã có lỗi xảy ra')
        })
}
document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.remove-favorite').forEach(function(element) {
        element.addEventListener('click', function(event) {
            event.preventDefault();
            removeFavorite(this);
        });
    });
});

function removeFavorite(element) {
    const movieId = element.getAttribute('movie-id');
    axios.delete(`/api/favorites?movieId=${movieId}`)
        .then(response => {
            toastr.success('Đã xóa khỏi danh sách yêu thích');
            const movieElement = element.closest('.col-2');
            if (movieElement) {
                movieElement.remove();
            }
        })
        .catch(error => {
            console.error('There was an error removing the favorite!', error);
            toastr.error('Có lỗi xảy ra khi xóa khỏi danh sách yêu thích');
        });
}