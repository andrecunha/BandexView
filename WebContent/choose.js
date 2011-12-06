choice = {
	"all" : "true"
};
a = new Object();
loadHTML = function() {
	window.open("cardapio.html?all=" + choice.all);
};
loadPDF = function() {
	window.open("cardapio.pdf?all=" + choice.all);
};
setAll = function() {
	choice.all = "true";
};
unsetAll = function() {
	choice.all = "false";
};