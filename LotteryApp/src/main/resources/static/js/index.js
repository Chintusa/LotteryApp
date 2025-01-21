//import Swal from 'sweetalert2';
  
// Utility function for debouncing

function debounce(func, delay) {
    let timeout;
    return function (...args) {
        clearTimeout(timeout);
        timeout = setTimeout(() => func.apply(this, args), delay);
    };
}


async function checkFormFields() {
  const mail = document.getElementById('email');
  const mailfeedback = document.getElementById('email-feedback');
  const pass= document.getElementById('password');
  const passfeedback = document.getElementById('password-feedback');
  const repass= document.getElementById('repassword');
  const repassfeedback = document.getElementById('repassword-feedback');
  const otp= document.getElementById('otp');
  const otpfeedback = document.getElementById('otp-feedback');
  await new Promise(resolve => setTimeout(resolve, 1000)); 

  if (!mail.value){
	mailfeedback.textContent='';
  }
  if (!pass.value){
	passfeedback.textContent='';
  }
  if (!repass.value){
	repassfeedback.textContent='';
  }
  if (!otp.value){
	otpfeedback.textContent='';
  }
    
   
    
}

// Example usage: Call the function when a button is clicked
//document.getElementById('submitBtn').addEventListener('click', checkFormFields());

function isFieldEmpty(fieldId) {
    const fieldValue = document.getElementById(fieldId).value.trim();
    return fieldValue === '';  
}


    
  


       function validateEmail() {
            const email = document.getElementById('email').value;
            const feedback = document.getElementById('email-feedback');
            const regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;

            if (regex.test(email)) {
                feedback.textContent = 'Email is valid';
                feedback.classList.add('valid');
                feedback.classList.remove('invalid');
            } else {
                feedback.textContent = 'Please enter a valid email address';
                feedback.classList.add('invalid');
                feedback.classList.remove('valid');
            }
        }

        function validatePassword() {
            const password = document.getElementById('password').value;
            const feedback = document.getElementById('password-feedback');
            const regex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!@#$&])[A-Za-z\d!@#$&]{10,}$/;

            if (regex.test(password)) {
                feedback.textContent = 'Password is valid';
                feedback.classList.add('valid');
                feedback.classList.remove('invalid');
            } else {
                feedback.textContent = 'Password must be at least 10 characters long and include at least one uppercase letter, one lowercase letter, one digit, and one special character (!@#$&)';
                feedback.classList.add('invalid');
                feedback.classList.remove('valid');
            }
        }

        function validateRePassword() {
            const password = document.getElementById('password').value;
            const repassword = document.getElementById('repassword').value;
            const feedback = document.getElementById('repassword-feedback');

            if (password === repassword) {
                feedback.textContent = 'Passwords match';
                feedback.classList.add('valid');
                feedback.classList.remove('invalid');
            } else {
                feedback.textContent = 'Passwords do not match';
                feedback.classList.add('invalid');
                feedback.classList.remove('valid');
            }
        }

        function showOtpSection() {
            const passwordFeedback = document.getElementById('password-feedback');
            const rePasswordFeedback = document.getElementById('repassword-feedback');
            const emailFeedback = document.getElementById('email-feedback');


            if (passwordFeedback.classList.contains('valid') && rePasswordFeedback.classList.contains('valid') && emailFeedback.classList.contains('valid')&&(document.getElementById('name').value!=='')) {
                passwordFeedback.textContent='';
                rePasswordFeedback.textContent='';
                emailFeedback.textContent='';
                document.querySelector('.otp-section').style.display = 'block';
            } else {
                alert("Please fill out the fields correctly before proceeding!");
            }
        }
function isOtpEmpty() {
    const otpValue = document.getElementById('otp').value.trim();
    return otpValue === '';  // Returns true if OTP field is empty
}

function login() {
    const otp = document.getElementById('otp').value;
    const feedback = document.getElementById('otp-feedback');

    if (isOtpEmpty()) {
        alert("OTP field is empty!");
        return false; 
    }

    // Sending OTP to the backend
    fetch('/api/verify-otp', { // Adjust the endpoint as per your backend configuration
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ otp }), // Sending OTP as JSON
    })
    .then(response => response.json()) // Assuming backend responds with JSON
    .then(data => {
        if (data.valid) { // Assuming backend responds with { "valid": true }
            feedback.textContent = '';
            feedback.classList.remove('invalid');
            feedback.classList.add('valid');
            animateSubmission(); // Call your animation function
        } else {
            feedback.textContent = 'Invalid OTP, please try again!';
            feedback.classList.add('invalid');
            feedback.classList.remove('valid');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        feedback.textContent = 'An error occurred, please try again later.';
        feedback.classList.add('invalid');
        feedback.classList.remove('valid');
    });
}


          
        
        
        // Add animations on form submission
        function animateSubmission() {
            const form = document.querySelector('.registration-container');
    form.classList.add('submitted');
    form.style.transform = 'scale(0.95)';
    form.style.transition = 'transform 0.3s ease-in-out';

    setTimeout(() => {
        form.style.transform = 'scale(1)';
    }, 300);
return true;
}

// Attach event listeners with debounced validation
document.getElementById('password').addEventListener('input', debounce(validatePassword, 300));
document.getElementById('repassword').addEventListener('input', debounce(validateRePassword, 300));
checkFormFields();

async function sendOtp(email){
	try{
		const response=await fetch('/api/send-otp',{
			method:"POST",
			headers:{
				'Content-Type':'application/json'
			},
			body: JSON.stringify({email}),
		});
		if (response.ok) {
		            const data = await response.json();
		            alert(`OTP sent successfully to ${email}`);
		        } else {
		            const error = await response.json();
		            alert(`Failed to send OTP: ${error.message}`);
		        }
		    } catch (error) {
		        console.error('Error sending OTP:', error);
		        alert('An error occurred while sending OTP.');
		    }
}


document.getElementById("sendOtp").addEventListener('click',()=>{
const email=document.getElementById("email").value;
if(email){
	sendOtp(email);
}else{
	alert("Please Enter a valid E-mail!");
}
	
});

