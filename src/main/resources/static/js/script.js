function applyJob(event) {
	var data = $(event.target).data();
	var token = $("meta[name='_csrf']").attr("content");
	$.ajax({
		url: `/jobseekers/apply-job/${data.jobid}`,
		method: 'GET',
		headers: {'X-CSRF-TOKEN': token}
	}).then(function(res) {
		$('#apply-button').text('Applied').attr("disabled","disabled");
		$('#apply-success-alert').show();
	}).catch(function(err) {
		$('#apply-button').text('Applied').attr("disabled","disabled");
		$('#apply-fail-alert').show();
	});
}

$(function() {
	$('#apply-button').on('click', applyJob);
});