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