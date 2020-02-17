import React from 'react';
import '../Css/styles.css';
import { useRef } from 'react';
import Button from '@material-ui/core/Button';
import EditIcon from '@material-ui/icons/Edit';
import { Dialog } from '@material-ui/core';
import LockIcon from '@material-ui/icons/Lock';
import LanguageIcon from '@material-ui/icons/Language';
import TextField from '@material-ui/core/TextField';
import image from '../Image/user-male.jpg';


function Global() {
    return (
        <div className="edit-content">
            <TextField size='medium' label="Name"></TextField>
            <br></br>
            <TextField size='medium' label="Surname"></TextField>
            <br></br>
            <TextField size='medium' label="Email"></TextField>
            <br></br>
          

            <div style={{ textAlign: 'right' }}>

                <Button color='primary'>Save</Button>
            </div>
        </div>
    )
}
function Security() {
    return (
        <div className="edit-content">
            <br></br>
            <TextField size='medium' label="Password"></TextField>
            <br></br>

            <div style={{ textAlign: "right" }}>

                <Button color='primary'>Save</Button>
            </div>
        </div>
    )
}
function User() {
    // eslint-disable-next-line react-hooks/rules-of-hooks


    return (
        <div className="main-container"  >

            <GeneralInfo></GeneralInfo>
            <ActiveExams></ActiveExams>

        </div>
    );

}

function GeneralInfo() {
    const inputEl = useRef(null);
    const [open, setopen] = React.useState(false);
    const [context, setcontext] = React.useState(Global);
    const handleClick = event => setopen(true)


    const handleClose = () => setopen(false)
    const setcontexthandler = (e) => {
        if (e == 0)
            setcontext(Global)
        else setcontext(Security)
    }
    function uploadimagehandler() {
        inputEl.current.click();

    }
    return (
        <div className="general-row">
            <div className='image-container-row' >
                <div className="image-container" >
                    <img src={image} alt='userImage' className='image' ></img>
                    <div className="middle">
                        <Button onClick={uploadimagehandler} color='primary' variant='contained' >Upload</Button>
                    </div>
                </div>
                <input type="file" ref={inputEl} style={{ display: "none" }} ></input>
                <Button onClick={handleClick} variant="contained" color="primary" style={{ fontSize: "1.5vw" }}>  <EditIcon></EditIcon>Edit Info</Button>
                <Dialog maxWidth="xl" open={open} onClose={handleClose}

                >
                    <div style={{ padding: "2vw 2vw 10vw 1vw" }}>
                        <h2 style={{ textAlign: "center" }}>Edit Info</h2>
                        <div className="sidebox">
                            <Button onClick={() => setcontexthandler(0)} color='primary'><LanguageIcon></LanguageIcon> General</Button>

                            <Button onClick={() => setcontexthandler(1)} color='primary'><LockIcon></LockIcon> Security</Button>
                        </div>
                        {context}
                        <br></br>
                        <Button onClick={handleClose} color='primary'>Cancel</Button>
                    </div>

                </Dialog>

            </div>
            <div className='about-container-row'>

                <div className="about-container-list-item"><b>Name:</b> Ruben</div>
                <div className="about-container-list-item"><b>Surname:</b> Poghosyan</div>
                <div className="about-container-list-item"><b>Email:</b> poghosyan26@gmail.com</div>
               

            </div>
        </div>
    );
}

function ActiveExams() {
    return (
        <div className="activeExams-container">
            <h1 >Active Exams</h1>
            <div className="activeExamsList-container">
                <div className="activeExamsList-container-Item"><div className='title'>Physics</div></div>
                <div className="activeExamsList-container-Item"><div className='title'>Mathematics</div></div>
                <div className="activeExamsList-container-Item"><div className='title'>English</div>
                </div>

            </div>
        </div>
    )
}
export default User;