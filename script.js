function convertToAngle() {
    var h = document.getElementById('angle-h').value;
    var d = document.getElementById('angle-d').value;
    var res = document.getElementById('angle-r').value;
    var size = document.getElementById('angle-s').value;
    
    if (h && d && res && size) {
        if (h > 0 && d > 0 && res > 0 && size > 0) {
            degPerPix = ((Math.atan2(0.5*h, d)) / (0.5 * res)) * 180 / Math.PI;
            sizeInDeg = size * degPerPix;
            alert('Single pixel is equal to: ' + Number(degPerPix).toFixed(4) + '\n' 
            + 'Stimulus size in visual angle is equal to: ' + Number(sizeInDeg).toFixed(4));
        } else {
            alert('Enter non zero values.');
        }
    } else {
        alert('Enter all parameters in a box with numbers.');
    }
    
}

function convertToPixel() {
    var h = document.getElementById('pixel-h').value;
    var d = document.getElementById('pixel-d').value;
    var res = document.getElementById('pixel-r').value;
    var size = document.getElementById('pixel-s').value;

    if (h && d && res && size) {
        if (h > 0 && d > 0 && res > 0 && size > 0) {
            degPerPix = ((Math.atan2(0.5*h, d)) / (0.5 * res)) * 180 / Math.PI;
            sizeInDeg = size / degPerPix;
            alert('Single pixel is equal to: ' + Number(degPerPix).toFixed(4) + '\n' 
            + 'Stimulus size in pixels is equal to: ' + Number(sizeInDeg).toFixed(4));
        } else {
            alert('Enter non zero values.');
        }
    } else {
        alert('Enter all parameters in a box with numbers.');
    }
}