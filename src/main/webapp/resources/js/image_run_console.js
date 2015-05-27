
function hide_initialization_div() {
    $(".initialization_container").hide();
}

function hide_console_div() {
    $(".console_container").hide();
}

function show_console_div() {
    $(".console_container").show();
}

function console_view() {
    hide_initialization_div();
    show_console_div();
}

function exec_command() {
    console.log("ContainerId=" + containerId);
    var command = $("#command").val();

    $.post(contextPath + "/home/containers/exec_command",
        { "containerId" : containerId,"command": command}, function (data) {
            console.log("Udane wykonanie, otrzymano:" + data);
            $("#command_output").val(data);
    });
}