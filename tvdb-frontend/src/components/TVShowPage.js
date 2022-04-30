import React, { useState, useEffect } from 'react'
import axios from 'axios';
import { Navigate, useLocation } from 'react-router-dom';
import TVShowService from '../services/TVShowService'

const TVShowPage = props => {
    const location = useLocation();
    const [showID, setShowID] = useState("");

    const [show, setShow] = useState([]);

    useEffect(() => {
        setShowID(/[^/]*$/.exec(location.pathname)[0] || "" );
    }, [location]);

}

export default TVShowPage;