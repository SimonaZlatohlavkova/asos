import { createSlice, configureStore } from '@reduxjs/toolkit';
import { Provider, useDispatch, useSelector } from 'react-redux';

export const cartSlice = createSlice({
    name: 'cart',
    initialState: [],
    reducers: {
        addToCart: (state, action) => {
            const existingItem = state.find(item => item.id === action.payload.id);
            if (existingItem) {
                existingItem.numberOfItems += action.payload.numberOfItems;
            } else {
                state.push(action.payload);
            }
        },
        removeFromCart: (state, action) => {
            return state.filter(item => item.id !== action.payload.id);
        },
        decrementItemQuantity: (state, action) => {
            const existingItem = state.find(item => item.id === action.payload.id);
            if (existingItem) {
                if (existingItem.numberOfItems > 1) {
                    existingItem.numberOfItems -= 1;
                }
            }
        },
        loadCart: (state, action) => {
            return action.payload;
        },
        emptyCart: (state, action) => {
            return [];
        },
        updateCartItems: (state, action) => {
            const updates = action.payload; // Array of updated items
            updates.forEach(update => {
                const existingItem = state.find(item => item.id === update.id);
                if (existingItem) {
                    Object.assign(existingItem, {
                        name: update.name || existingItem.name,
                        originalPrice: update.originalPrice || existingItem.originalPrice,
                        salePrice: update.salePrice || existingItem.salePrice,
                        stock: update.stock || existingItem.stock,
                        url: update.url || existingItem.url
                    });
                }
            });
        },

    },
});
export const { addToCart, emptyCart, removeFromCart, decrementItemQuantity, updateCartItems,loadCart } = cartSlice.actions;
 export const Cart = () => {
    const dispatch = useDispatch();
    const cart = useSelector((state) => state.cart);

    return (
        <div>
            <button onClick={() => dispatch(addToCart({ id: 1, name: 'Item A' , numberOfItems:5 }))}>
                Add to Cart
            </button>
            <ul>
                {cart.map((item) => (
                    <li key={item.id}>{item.name}</li>
                ))}
            </ul>
        </div>
    );
};
