let soundCounter = -1;
let playList = [];

let dobasokMp3 = new Array(
    new Audio("assets/mp3/dobas0.mp3"),
    new Audio("assets/mp3/dobas1.mp3"),
    new Audio("assets/mp3/dobas2.mp3")
);

let fingokMp3 = new Array(
    new Audio("assets/mp3/fing0.mp3"),
    new Audio("assets/mp3/fing1.mp3"),
    new Audio("assets/mp3/fing2.mp3")
);

let fanfarokMp3 = new Array(
    new Audio("assets/mp3/szeref0.mp3"),
    new Audio("assets/mp3/szeref1.mp3"),
    new Audio("assets/mp3/szeref2.mp3")
);

let szerencsekMp3 = new Array(
    new Audio("assets/mp3/szer0.mp3"),
    new Audio("assets/mp3/szer1.mp3"),
    new Audio("assets/mp3/szer2.mp3"),
    new Audio("assets/mp3/szer3.mp3")
);

let sikerekMp3 = new Array(
    new Audio("assets/mp3/siker0.mp3"),
    new Audio("assets/mp3/siker1.mp3"),
    new Audio("assets/mp3/siker2.mp3"),
    new Audio("assets/mp3/siker3.mp3"),
    new Audio("assets/mp3/siker4.mp3"),
    new Audio("assets/mp3/siker5.mp3"),
    new Audio("assets/mp3/siker6.mp3"),
    new Audio("assets/mp3/siker7.mp3"),
    new Audio("assets/mp3/siker8.mp3"),
    new Audio("assets/mp3/siker9.mp3"),
    new Audio("assets/mp3/siker10.mp3"),
    new Audio("assets/mp3/siker11.mp3"),
    new Audio("assets/mp3/siker12.mp3"),
    new Audio("assets/mp3/siker13.mp3"),
    new Audio("assets/mp3/siker14.mp3"),
    new Audio("assets/mp3/siker15.mp3")
);


let sikertErtelEl = new Audio("assets/mp3/sikertertel.mp3");

function playSound() {
    soundCounter++;
    if (soundCounter == playList.length) {
        soundCounter = -1;
        playList = [];
        return;
    }
    playList[soundCounter].addEventListener('ended', playSound);
    playList[soundCounter].play();
}

function playDobas(sikerek) {
    if (sikerek == 0) {
        playList.push(fingokMp3[Math.floor(Math.random() * 3)]);
        playList.push(szerencsekMp3[0]);
        playSound();
    } else {
        playList.push(sikerekMp3[sikerek]);
        playList.push(sikertErtelEl);
        playSound();
    }
}

function playSzerencseDobas(sikerek) {
    if (sikerek == 0) {
        playList.push(fingokMp3[Math.floor(Math.random() * 3)]);
        playList.push(szerencsekMp3[0]);
        playSound();
    } else {
        playList.push(fanfarokMp3[sikerek - 1]);
        playList.push(szerencsekMp3[sikerek]);
        playSound();
    }
}