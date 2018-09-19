function ityzl_SHOW_LOAD_LAYER(){  
	 return layer.msg('数据加载中...', {
         icon:16
        ,anim: -1 
        ,fixed: false
        ,time:100000000
        ,skin:'winning-class'
      }) 
      
}  
function ityzl_CLOSE_LOAD_LAYER(index){  
    layer.close(index);  
}