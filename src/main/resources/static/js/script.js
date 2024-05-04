const mouseLeft = document.getElementById("mouseLeft");
const mouseRight = document.getElementById("mouseRight");
const mouseGrip = document.getElementById("gripMouse");

const touch = document.getElementById("touchField");
const touchLabel = document.getElementById("touchLabel");
const touchLabelStart = document.getElementById("touchLabelStart");
const touchBound = touch.getBoundingClientRect();

let startX = 0;
let startY = 0;

function click(button) {
    fetch(`/control/click?button=${button}`, { method: 'GET' });
}

mouseLeft.addEventListener("click", () => {
    click("MOUSE_LEFT");
});

mouseRight.addEventListener("click", () => {
    click("MOUSE_RIGHT");
});

touch.addEventListener("touchstart", function(e) {
    startX = e.touches[0].clientX - touchBound.left;
    startY = e.touches[0].clientY - touchBound.top;

    touchLabelStart.innerText = `Position - X: ${startX}, Y: ${startY}`;
})

touch.addEventListener("touchmove", function(e) {
    let x = e.touches[0].clientX - touchBound.left;
    let y = e.touches[0].clientY - touchBound.top;

    touchLabel.innerText = `Movement - X: ${x}, Y:${y}`;

    let movementPositionX = Math.round((x - startX) * 0.1);
    let movementPositionY = Math.round((y - startY) * 0.1);

    fetch(`/control/move?x=${movementPositionX}&y=${movementPositionY}`, { method: 'GET' });
});
