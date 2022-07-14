import React, { useState } from 'react'
import "./Dropdown.css"
import { Category } from './Category'

const categories = ["All", "Movie", "Television Series", "Documentary"]

const Dropdown = ({handleCategory}) => {

    const [isActive, setIsActive] = useState(false)
    const [categoryName, setCategoryName] = useState("none")

    return(

        <div className='dropdown'>

            <div className='dropdown-button' 
                onMouseOver={() => setIsActive(true)}
                onMouseLeave={() => setIsActive(false)}
            >Categories</div>


            {isActive && (
                <div className='dropdown-categories'
                onMouseOver={() => setIsActive(true)}
                onMouseLeave={() => setIsActive(false)}>
                    {
                        categories.map((info, index) => {
                            return <Category info={info} key={index} handleCategory={setCategoryName}/>
                        })
                    }
                </div>
            )}

            {handleCategory(categoryName)}

        </div>

    )


}

export default Dropdown;