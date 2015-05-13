var container_handler = {
    add_container_visibility : false,

    init : function() {
        container_view.init();
    },

    toggle_add_cointainer_visibility : function() {
        this.add_container_visibility = !this.add_container_visibility;
        if (this.add_container_visibility) {
            container_view.show_add_container_form();
        } else {
            container_view.hide_add_container_form();
        }
    }
};

var container_view = {
    init : function() {
        this.hide_add_container_form();
        $("#show_hide_add_container").click(function() {
            container_handler.toggle_add_cointainer_visibility();
        });
    },

    hide_add_container_form : function() {
        $(".add_container").hide();
    },

    show_add_container_form : function() {
        $(".add_container").show();
    }
}

$(document).ready(function() {
    container_handler.init();
});
