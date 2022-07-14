import React from 'react';
import { Link } from 'react-router-dom';
import "./Card.css"


const Card = (movie) => {

    return(

    <Link state={{movieAttributes:{...movie.info}}} to="/FilmProfile">

        <div className='card'>
            <img src={movie.info.productUrl} className='image' alt=""/>

            <div className='cardDetails'>

                <h5 className='cardTitle'>{movie.info.productName}</h5>
                <b className='cardAverage'>{movie.info.productRate}</b>

            </div>

        </div>
        
    </Link>

    )

}

export default Card;