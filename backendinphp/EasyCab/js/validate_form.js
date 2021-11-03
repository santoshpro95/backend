
//functions check email

function checkEmail(strng)
{
    var error = false;
    if (strng == "") {
        error = true;
    }
    var emailFilter = /^.+@.+\..{2,3}$/;
    if (!(emailFilter.test(strng))) {
        error = true;
    } else {
        //test email for illegal characters
        var illegalChars = /[\(\)\<\>\,\;\:\\\"\[\]]/
        if (strng.match(illegalChars)) {
            error = true;
        }
    }
    return error;
}
function lengthRestriction(elem, min, max) {
    var uInput = elem.value;
    if (uInput.length > min && uInput.length < max) {
        return true;
    } else {
        //alert("Please enter between " +min+ " and " +max+ " characters");
        elem.focus();
        return false;
    }
}
function IsNumeric(expression)
{
    var nums = "0123456789";
    if (expression.length == 0)
    {
        return(false);
    }
    for (var n = 0; n < expression.length; n++)
    {
        if (nums.indexOf(expression.charAt(n)) == -1) {
            return(false);
        }
    }
    return(true);
}
function IsName(expression)
{
    var nums = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    if (expression.length == 0)
        return(false);
    for (var n = 0; n < expression.length; n++)
    {
        if (nums.indexOf(expression.charAt(n)) == -1)
            return(false);
    }
    return(true);
}
//function 
function checkform() {
    // radio button validation for user type 
    var elem = document.forms['registration'].elements['rdoregister'];
    len = elem.length - 1;
    chkvalue = '';
    for (i = 0; i <= len; i++)
    {
        if (elem[i].checked)
            chkvalue = elem[i].value;
    }
    if (chkvalue == '')
    {
        alert('Please select registration as.');
        return false;
    }

    //radio button validation for ends here usertype
    var firstname = document.getElementById("firstname");
    if (firstname.value.trim() == "") {
        alert('Enter Your First Name.');
        firstname.focus();
        return false;
    }
    if (!IsName(document.getElementById("firstname").value))
    {
        alert("Please enter Name Correctly");
        return false;
    }

    var lastname = document.getElementById("lastname");
    if (lastname.value.trim() == "") {
        alert('Enter Your Last Name.');
        lastname.focus();
        return false;
    }
    if (!IsName(document.getElementById("lastname").value))
    {
        alert("Please enter Name Correctly");
        return false;
    }

    var email = document.getElementById("email");
    if (email.value.trim() == "") {
        alert('Please enter your  Email Address');
        email.focus();
        return false;
    }
    if (checkEmail(document.getElementById("email").value))
    {
        alert("Please enter your Correct Email Address");
        return false;
    }
    var mobile = document.getElementById("mobile");
    if (mobile.value.trim() == "") {
        alert(' Please Enter Your  Mobile or Phone No.');
        mobile.focus();
        return false;
    }
    if (!IsNumeric(document.getElementById("mobile").value))
    {
        alert("Please enter your mobile no. correctly");
        return false;
    }
    if (!lengthRestriction(document.getElementById('mobile'), 9, 11))
    {
        alert("Mobile number must contain 10 digits.");
        return false;
    }
    var password = document.getElementById("password");
    if (password.value.trim() == "") {
        alert(' Please Enter Your  Password');
        password.focus();
        return false;
    }
    var confirm = document.getElementById("confirm");
    if (confirm.value.trim() == "") {
        alert(' please enter your  confirm password');
        confirm.focus();
        return false;
    }
    if (password.value.trim() != confirm.value.trim()) {
        alert('Confirm password does not matched.');
        confirm.focus();
        return false;
    }

}
//          End of register *************************  

//functions check email
function checkforms() {
    var firstname = document.getElementById("firstname");
    if (firstname.value.trim() == "") {
        alert('Enter Your First Name.');
        firstname.focus();
        return false;
    }
    if (!IsName(document.getElementById("firstname").value))
    {
        alert("Please enter Name Correctly");
        return false;
    }

    var lastname = document.getElementById("lastname");
    if (lastname.value.trim() == "") {
        alert('Enter Your Last Name.');
        lastname.focus();
        return false;
    }
    if (!IsName(document.getElementById("lastname").value))
    {
        alert("Please enter Name Correctly");
        return false;
    }

    var email = document.getElementById("email");
    if (email.value.trim() == "") {
        alert('Please enter your  Email Address');
        email.focus();
        return false;
    }
    if (checkEmail(document.getElementById("email").value))
    {
        alert("Please enter your Correct Email Address");
        return false;
    }
    var mobile = document.getElementById("mobile");
    if (mobile.value.trim() == "") {
        alert(' Please Enter Your  Mobile or Phone No.');
        mobile.focus();
        return false;
    }
    if (!IsNumeric(document.getElementById("mobile").value))
    {
        alert("Please enter your mobile no. correctly");
        return false;
    }
    if (!lengthRestriction(document.getElementById('mobile'), 9, 11))
    {
        alert("Mobile number must contain 10 digits.");
        return false;
    }


}

function returnsubmitform(){
  var occupation = document.getElementById("occupation");
    if (occupation.value.trim() == "") {
        alert(' Please enter your  occupation.');
        occupation.focus();
        return false;
    }
  var source_income = document.getElementById("source_income");
    if (source_income.value.trim() == "") {
        alert(' Please enter your  source_income.');
        occupation.focus();
        return false;
    } 
    var annual_income = document.getElementById("annual_income");
    if (annual_income.value.trim() == "") {
        alert(' Please enter your  annual_income.');
        annual_income.focus();
        return false;
    }
    var annual_income = document.getElementById("annual_income");
    if (annual_income.value.trim() == "") {
        alert(' Please enter your  annual_income.');
        annual_income.focus();
        return false;
    }
    var nooffamilty = document.getElementById("nooffamilty");
    if (nooffamilty.value.trim() == "") {
        alert(' Please enter your  nooffamilty.');
        nooffamilty.focus();
        return false;
    }
    var nationality = document.getElementById("nationality");
    if (nationality.value.trim() == "") {
        alert(' Please enter your  nationality.');
        nationality.focus();
        return false;
    }
     var gender = document.getElementById("gender");
    if (gender.value.trim() == "") {
        alert(' Please select your  gender.');
        gender.focus();
        return false;
    }
    var address = document.getElementById("address");
    if (address.value.trim() == "") {
        alert(' Please enter your  address.');
        address.focus();
        return false;
    }
     var pin = document.getElementById("pin");
    if (pin.value.trim() == "") {
        alert(' Please enter your  pin.');
        pin.focus();
        return false;
    }
    var city = document.getElementById("city");
    if (city.value.trim() == "") {
        alert(' Please enter your  city.');
        city.focus();
        return false;
    }
     var state = document.getElementById("state");
    if (state.value.trim() == "") {
        alert(' Please enter your  state.');
        state.focus();
        return false;
    }
    var panno = document.getElementById("panno");
    if (panno.value.trim() == "") {
        alert(' Please enter your  panno.');
        panno.focus();
        return false;
    }
     var loan_amount = document.getElementById("loan_amount");
    if (loan_amount.value.trim() == "") {
        alert(' Please enter your  loan_amount.');
        loan_amount.focus();
        return false;
    }
    var interest_rates = document.getElementById("interest_rates");
    if (interest_rates.value.trim() == "") {
        alert(' Please enter your  interest_rates.');
        interest_rates.focus();
        return false;
    }
     var years = document.getElementById("years");
    if (years.value.trim() == "") {
        alert(' Please select your  years.');
        years.focus();
        return false;
    }
    var emi = document.getElementById("emi");
    if (emi.value.trim() == "") {
        alert(' Please enter your  emi.');
        emi.focus();
        return false;
    }
     var file = document.getElementById("file");
    if (file.value.trim() == "") {
        alert(' Please choose your  file.');
        file.focus();
        return false;
    }
}