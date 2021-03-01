//타임 라인 이동시키기

(function() {

    // VARIABLES
    const timeline = document.querySelector(".timeline ol"),
        // elH = document.querySelectorAll(".timeline li > div"),
        arrows = document.querySelectorAll(".timeline .arrows .arrow"),
        arrowPrev = document.querySelector(".timeline .arrows .arrow__prev"),
        arrowNext = document.querySelector(".timeline .arrows .arrow__next"),
        firstItem = document.querySelector(".timeline li:first-child"),
        lastItem = document.querySelector(".timeline li:last-child"),
        xScrolling = 280,
        disabledClass = "disabled";

    // START
    window.addEventListener("load", init);

    function init() {
        // setEqualHeights(elH);
        animateTl(xScrolling, arrows, timeline);
    }

    // // SET EQUAL HEIGHTS
    // function setEqualHeights(el) {
    //     let counter = 0;
    //     for (let i = 0; i < el.length; i++) {
    //         const singleHeight = el[i].offsetHeight;

    //         if (counter < singleHeight) {
    //             counter = singleHeight;
    //         }
    //     }

    //     for (let i = 0; i < el.length; i++) {
    //         el[i].style.height = `${counter}px`;
    //     }
    // }

    // CHECK IF AN ELEMENT IS IN VIEWPORT
    // http://stackoverflow.com/questions/123999/how-to-tell-if-a-dom-element-is-visible-in-the-current-viewport
    function isElementInViewport(el) {
        const rect = el.getBoundingClientRect();
        return (
            rect.top >= 0 &&
            rect.left >= 0 &&
            rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) &&
            rect.right <= (window.innerWidth || document.documentElement.clientWidth)
        );
    }

    // SET STATE OF PREV/NEXT ARROWS
    function setBtnState(el, flag = true) {
        if (flag) {
            el.classList.add(disabledClass);
        } else {
            if (el.classList.contains(disabledClass)) {
                el.classList.remove(disabledClass);
            }
            el.disabled = false;
        }
    }

    // ANIMATE TIMELINE
    function animateTl(scrolling, el, tl) {
        let counter = 0;
        for (let i = 0; i < el.length; i++) {
            el[i].addEventListener("click", function() {
                if (!arrowPrev.disabled) {
                    arrowPrev.disabled = true;
                }
                if (!arrowNext.disabled) {
                    arrowNext.disabled = true;
                }

                const sign = (this.classList.contains("arrow__prev")) ? "" : "-";
                if (counter === 0) {
                    tl.style.transform = `translateX(-${scrolling}px)`;
                } else {
                    const tlStyle = getComputedStyle(tl);
                    // add more browser prefixes if needed here
                    const tlTransform = tlStyle.getPropertyValue("-webkit-transform") || tlStyle.getPropertyValue("transform");
                    const values = parseInt(tlTransform.split(",")[4]) + parseInt(`${sign}${scrolling}`);
                    tl.style.transform = `translateX(${values}px)`;
                }

                setTimeout(() => {
                    isElementInViewport(firstItem) ? setBtnState(arrowPrev) : setBtnState(arrowPrev, false);
                    isElementInViewport(lastItem) ? setBtnState(arrowNext) : setBtnState(arrowNext, false);
                }, 1100);

                counter++;
            });
        }
    }

})();

// history 클릭 시 detailview 제공
function showDetail(e) {
    var l = e.classList
    var imageClassList = e.lastChild.previousSibling.classList
    if (l.contains("detail-view")) {
        l.remove("detail-view")
        imageClassList.add("hide")
    } else {
        l.add("detail-view")
        imageClassList.remove("hide")
    }
}

// 플레이 버튼 (비동기 실행 위해서 sleep 완성해야 함)
function sleep(ms) {
    const wakeUpTime = Date.now() + ms
    while (Date.now() < wakeUpTime) {}
}

function playHistory() {
    var historyList = document.getElementsByClassName("view");
    console.log(historyList);
    for (let i = 0; i < historyList.length; i++) {
        showDetail(historyList[i]);
        sleep(10000);
        showDetail(historyList[i]);
    }
}