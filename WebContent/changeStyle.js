none = "";
android = "styles/android.css";
android_mobile = "styles/android-mobile.css";
atmosphere = "styles/atmosphere.css";
atmosphere_mobile = "styles/atmosphere-mobile.css";
greenZap = "styles/greenzap.css";
greenZap_mobile = "styles/greenzap-mobile.css";

/* Changes the stylesheet used to format the current document. */
changeStyle = function(style) {
	document.getElementById("docstyle").setAttribute("href", style);
};