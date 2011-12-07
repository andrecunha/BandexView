choice = {
	"all" : "true" /* True if user wants to see the whole menu; false
	if he wants to see only the dessert. */
};
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