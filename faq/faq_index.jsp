<%-- 
    Document   : index
    Created on : Sep 23, 2022, 9:35:34 AM
    Author     : Thien's
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content=
              "width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href=
              "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity=
              "sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />

        <link href="https://fonts.googleapis.com/css2?family=Lato&family=Roboto+Slab&family=Spartan:wght@400;700&display=swap" rel="stylesheet">

        <style>        


            h1 {
                text-align: center;
                font-family: "Roboto Slab", serif;
                font-size: 40px;
            }

            li {
                list-style: none;
                margin-bottom: 10px;
                padding: 15px;
                border-bottom: 2px solid #6d6b6b;
            }

            .question {
                font-family: "Spartan", sans-serif;
                font-size: 20px;
                font-weight: 600;
                margin-bottom: 10px;
                position: relative;
                color: #5c5a5a;
            }

            .icon-main {
                position: absolute;
                right: 0px;
                cursor: pointer;
            }

            .non-active {
                display: none;
            }

            .answer {
                font-size: 17px;
                font-family: "Spartan", sans-serif;
            }
        </style>
        <title>FAQs - Topics</title>
    </head>
    <body>


        <!-- Featured Section Begin -->
        <div class="container" >
            <h1>FAQs - Topics</h1>
            <ul>
                <li class="faq">
                    <div class="question">
                        How can we help ?
                        <span class="icon-main">
                            <i class="fa-solid fa-plus"></i>
                        </span>
                    </div>
                    <div class="answer non-active">
                        GeeksforGeeks is the best Computer
                        Science portal for geeks. It contains
                        well written, well thought and well
                        explained computer science and
                        programming articles.
                    </div>
                </li>
                <li class="faq">
                    <div class="question">
                        How to post my CV ? 
                        <span class="icon-main">
                            <i class="fa-solid fa-plus"></i>
                        </span>
                    </div>
                    <p class="answer non-active">
                        the Frequently Asked Questions (FAQ)
                        section is a part of your website
                        where you address common concerns,
                        questions,and objections that
                        customers have.
                    </p>

                </li>
                <li class="faq">
                    <div class="question">
                        How do I access and edit my profile  ? 
                        <span class="icon-main">
                            <i class="fa-solid fa-plus"></i>
                        </span>
                    </div>
                    <p class="answer non-active">
                        1. Đăng nhập vào tài khoản của bạn và nhấp chuột vào Hồ Sơ Xin Việc (Resumes) trên thanh chuyển động gần sát đầu trang.
                        <br>
                        2. Nhấp vào Hiệu chỉnh (Edit) bên dưới tiêu đề Hồ sơ xin việc của bạn.
                        <br>
                        3. Nhấp vào Thông tin ứng viên (Candate Info) trên danh sách những phần của Hồ sơ Xin Việc. Trên trang này, bạn có thể thay đổi số điện thoại mà bạn đã nhập vào trước đây.
                        <br>
                        4. Nhấp Lưu (Save).
                    </p>
                </li>
                <li class="faq">
                    <div class="question">
                        How can I see the jobs I have applied for?
                        <span class="icon-main">
                            <i class="fa-solid fa-plus"></i>
                        </span>
                    </div>
                    <p class="answer non-active">
                        Bạn có thể xem những Hồ sơ xin việc bạn đã nộp bằng cách đăng nhập vào tài khoản của bạn 
                        và nhấp chuột vào Profile.
                        <br>
                        All the applications will be there.
                        <br>
                        You can delete what applications, you do not want by clicking the button [Delete].
                    </p>
                </li>
                <li class="faq">
                    <div class="question">
                        Can I attach multiple files when applying ?
                        <span class="icon-main">
                            <i class="fa-solid fa-plus"></i>
                        </span>
                    </div>
                    <p class="answer non-active">
                        Only PDF File.
                        <br>
                        Resize your profile is less than 3,072 KB.
                        <br>
                        When applying online through 3HTD, you can only attach 01 file.
                    </p>
                </li>
                <li class="faq">
                    <div class="question">
                        Cách thức để trở thành Candidate
                        <span class="icon-main">
                            <i class="fa-solid fa-plus"></i>
                        </span>
                    </div>
                    <p class="answer non-active">

                        <Đăng nhập bằng Personal Email có đầy đủ thông tin.
                        <br>
                        2. Chọn Công việc muốn ứng tuyển, and upload your suitable resumes.
                        <br>
                        Nếu được chấp thuận CV, bạn sẽ được yêu cầu để làm 1 bài Exam.
                        <br>
                        Nếu pass Exem, bạn sẽ được lên lịch để đi phỏng vấn.
                        <br>
                        Nếu đậu được phỏng vấn, bạn sẽ trở thành 1 trong những Candidate của chúng tôi.
                        <br>
                    </p>
                </li>
                <li class="faq">
                    <div class="question">
                        Liên hệ với 3HTD Recruitment System
                        <span class="icon-main">
                            <i class="fa-solid fa-plus"></i>
                        </span>
                    </div>
                    <p class="answer non-active">
                        Bạn gặp khó khăn trong quá trình đăng nhập hoặc trải nghiệm 3HTD Recruitment System?

                        <br>Chúng tôi rất vui và sẵn sàng hỗ trợ bạn nhanh chóng nhất có thể. 
                        Bạn có thể liên hệ với chúng tôi theo một trong những cách sau:


                        <br>Nếu cần hỗ trợ thêm hoặc với các vấn đề , 
                        bạn có thể liên hệ qua mẫu thư liên lạc hoặc gửi email đến contact@3htd.com, 
                        các chuyên viên tư vấn của VietnamWorks sẽ trả lời bạn trong thời gian sớm nhất.
                    </p>
                </li>
                <li class="faq">
                    <div class="question">
                        a
                        <span class="icon-main">
                            <i class="fa-solid fa-plus"></i>
                        </span>
                    </div>
                    <p class="answer non-active">
                        a
                    </p>
                </li>
            </ul>
            <center>
<!--                <input style="text-align: center" type="button" id="feedback-form" name="feedback" value="We'd like your feedback" >-->
                <button><a href="<c:url value='/home?op=faq_index'/>">We'd like your feedback</a></button>
                <button><a href="<c:url value='/faq?op=list'/>">We'd like your feedback</a></button>

            </center>
        </div>
                
        <script>
            //Show more (+) - Show less (x)
            const faqs = document.querySelectorAll(".faq");
            for (const item of faqs) {
                const curr_faq = item.childNodes;
                console.log(curr_faq);
                const question = curr_faq[1];
                const answer = curr_faq[3];
                const icon = question.querySelector(".icon-main");
                icon.addEventListener("click", function () {
                    answer.classList.toggle("non-active");
                    const i = icon.querySelector("i");
                    i.classList.toggle("fa-xmark");
                    i.classList.toggle("fa-plus");
                });
            }

            
        </script>

        <!-- Js Plugins -->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/popUp.js"></script>

    </body>
</html>
