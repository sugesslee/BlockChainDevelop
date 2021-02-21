document.documentElement.style.fontSize = document.documentElement.clientWidth / 3.75 + 'px';
window.onload=function() {
  document.documentElement.style.fontSize = document.documentElement.clientWidth / 3.75 + 'px';
  if (document.documentElement.clientWidth > 414) {
    document.documentElement.style.fontSize = 414 / 3.75 + 'px'
  }
};
window.onresize=function(){
  document.documentElement.style.fontSize = document.documentElement.clientWidth / 3.75 + 'px';
  if (document.documentElement.clientWidth > 414) {
    document.documentElement.style.fontSize = 414 / 3.75 + 'px'
  }
};
