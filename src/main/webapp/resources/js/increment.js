$(function() {

  //$(".numbers-row").append('<div class="inc button">+</div><div class="dec button">-</div>');
  $(".numbers-row").append('<div class="inc button">+</div><div class="dec button">-</div>');
  $(".button").on("click", function() {

    var $button = $(this);
    var oldValue = $button.parent().find("input").val();

    if ($button.text() == "+") {
  	  var newVal = parseFloat(oldValue) + 1;
  	} else {
      if (oldValue > 1) {
        var newVal = parseFloat(oldValue) - 1;
	    } else {
        newVal = 1;
      }
	  }

    $button.parent().find("input").val(newVal);

  });

});