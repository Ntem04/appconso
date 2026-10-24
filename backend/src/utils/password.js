// --- Password Hashing Utilities using bcrypt ---
import bcrypt from 'bcryptjs';

/**
 * Hash a plain text password using bcrypt
 * @param {string} password
 * @returns {Promise<string>}
 */
export const hashPassword = async (password) => {
    const saltRounds = 10;
    return await bcrypt.hash(password, saltRounds);
};

/**
 * Compare a plain text password with a bcrypt hash
 * @param {string} password
 * @param {string} hash
 * @returns {Promise<boolean>}
 */
export const comparePassword = async (password, hash) => {
    return await bcrypt.compare(password, hash);
};
