document.write('<link href=' + FEED_BACK_HOST
		+ '"/css/feedback.css" rel="stylesheet" type="text/css" />');
document.write('<script src="' + FEED_BACK_HOST
		+ '/js/fabric.min.js"></script>');
document.write('<script src="' + FEED_BACK_HOST
		+ '/js/html2canvas.js"></script>');
document.write('<script src="' + FEED_BACK_HOST + '/js/feedback.js"></script>');

Feedback({
	url: FEED_BACK_HOST + '/api/feedback',
	apikey: FEED_BACK_TOKEN
});