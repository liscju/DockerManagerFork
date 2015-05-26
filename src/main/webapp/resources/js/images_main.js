jQuery(document).ready(function ($) {
    $('#tabs').tab();
});

function searchForImage() {
    var image_to_find = $("#image_to_find").first().val();
    var tableResult = $("#search-result-body").first();
    $.get("images/find_image?image_to_find="+image_to_find, function(data) {
        tableResult.empty();
        for (var image in data) {
            tableResult.append(
                "<tr>" +
                    "<td>" + image + "</td>" +
                    "<td>" + data[image] + "</td>" +
                    "<td>" +
                        "<form method=\"post\" action=\"images/pull_image\">" +
                            "<input type=\"hidden\" name=\"image_to_pull\" value=\"" + image + "\">" +
                            "<button class=\"btn btn-default\">" +
                                "Pull Image" +
                            "</button>" +
                        "</form>" +
                    "</td>" +
                "</tr>"
            );
        }
    }).fail(function() {
        tableResult.empty();
        tableResult.append("<h3 style=\"color:red\">Error connecting with server</h3>");
    });
}