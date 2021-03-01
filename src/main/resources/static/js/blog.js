// function dp_board() {
//     let click = document.getElementsByClassName("inner-content");
//     for (let i = 0; click.length > i; i++) {

//         if (click[i].style.display === "none") {
//             click[i].style.display = "block";

//         } else {
//             click[i].style.display = "none";

//         }
//     }
// }

function dp_board(e) {
    var content = e.nextSibling.nextSibling;
    if (content.style.display === "none") {
        content.style.display = "block";
    } else {
        content.style.display = "none";
    }
}